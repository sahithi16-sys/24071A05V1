const exp = require('express')
const jwt = require('jsonwebtoken')
const bcrypt = require('bcryptjs')

const app = exp()
const PORT = 3000
const JWT_SECRET = 'your-secret-key' // In production, use environment variable

app.use(exp.json())

const users = [
  {
    id: 1,
    username: 'admin',
    password: bcrypt.hashSync('password123', 10) // Hashed password
  }
]

const authenticateToken = (req, res, next) => {
  const authHeader = req.headers['authorization']
  const token = authHeader && authHeader.split(' ')[1] // Bearer TOKEN

  if (!token) {
    return res.status(401).json({ message: 'Access token required' })
  }

  jwt.verify(token, JWT_SECRET, (err, user) => {
    if (err) {
      return res.status(403).json({ message: 'Invalid or expired token' })
    }
    req.user = user
    next()
  })
}

let students = [
  {id : 101, name: 'Alice' , age: 21, branch: 'CSE'},
  {id: 102, name: 'Bob', age: 20, branch: 'IT'}
];

const findStudentById = (id) => { return students.find((student) => student.id===id)}

app.get('/',(req,res) => {
  res.send("Student CRUD API with JWT Authentication is running")
})

app.post('/login', (req, res) => {
  const { username, password } = req.body

  if (!username || !password) {
    return res.status(400).json({ message: 'Username and password are required' })
  }

  const user = users.find(u => u.username === username)
  if (!user) {
    return res.status(401).json({ message: 'Invalid credentials' })
  }

  const validPassword = bcrypt.compareSync(password,user.password)

  if(!validPassword) return res.status(401).json({message: 'Incorrect Password'})

    const token = jwt.sign({
      id: user.id,
      username: user.username
    },JWT_SECRET ,{expiresIn: '1h'})

    res.json({
      message: 'Login Successful',
      token: token,
      user: {id: user.id, username: user.username}
    })
})

app.post('/students',authenticateToken,(req,res)=> {
  
  const {name,age,branch} = req.body

  if(!name || !age || !branch) return res.status(401).json({message : 'Name, Age and Branch are required'})
    const id = students.length? students[students.length-1].id + 1 : 1;
  const newStudent = {
    id : id,
    name,
    age,
    branch
  }

  students.push(newStudent)
  res.status(200).json({message: 'Student Added successfully', newStudent})
})

app.get('/students',authenticateToken,(req,res)=> {
  res.status(201).json(students)
})

app.get('/students/:id',authenticateToken,(req,res)=> {

  const id = Number(req.params.id)
  const student = findStudentById(id)

  if(!student) return res.status(404).json({message: 'Student Not Found'})

    res.status(201).json(student)
})

app.put('/students/:id',(req,res) => {
  const id = Number(req.params.id)
  const student = findStudentById(id)

  if(!student) return res.status(404).json({message: 'Student Not Found'})

  const {name,age,branch} = req.body
  if(!name || !age || !branch) return res.status(401).json({message : 'Name, Age and Branch are required'})

    student.name = name
    student.age = age
    student.branch = branch

    res.status(201).json({message: 'Student Updated Successfully'})

})

app.delete('/students/:id', (req,res)=> {

    const id = Number(req.params.id)
  const student = findStudentById(id)

  if(!student) return res.status(404).json({message: 'Student Not Found'})

    students = students.filter((s)=> s.id!==id)

    res.status(201).json({message: 'Student Deleted Successfully'})
})

app.listen(PORT,()=> {
  console.log(`Server currently running at http://localhost:${PORT}`)
})