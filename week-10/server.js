const exp = require('express')

const app = exp()
const PORT = 3000

app.use(exp.json())

let students = [
  {id : 101, name: 'Alice' , age: 21, branch: 'CSE'},
  {id: 102, name: 'Bob', age: 20, branch: 'IT'}
];

const findStudentById = (id) => { return students.find((student) => student.id===id)}

app.get('/',(req,res) => {
  res.send("Student CRUD API is running")
})

app.get('/students',(req,res)=> {
  res.status(200).json(students)
})

app.get('/students/:id', (req,res) => {
  const id = Number(req.params.id)
  const student = findStudentById(id);
  console.log("Current students:", students);

  if(!student) {
    return res.status(404).json({ message: 'Student not found' })
  }

  res.status(200).json(student)
})

app.post('/students', (req,res) => {
  const {name,age,branch} = req.body

  if(!name || !age || !branch) return res.status(400).json('Name, Age and Branch all fields are required')
  
    const id = students.length? students[students.length-1].id+1 : 1;
    const newStudent = {
      id: id,
      name,
      age,
      branch
    }

    students.push(newStudent);
    res.status(201).json({message: 'Student Added Successfully',newStudent})
})

app.put('/students/:id', (req,res) => {
  const id = Number(req.params.id)

  const student = findStudentById(id)

  if(!student) {
    return res.status(404).json({message: 'Student Not Found'})
  }

    const {name,age,branch} = req.body

  if(!name || !age || !branch) return res.status(400).json('Name, Age and Branch all fields are required')
    student.name = name
    student.age = age
    student.branch = branch

    return res.status(200).json({message: 'Student Updated Successfully'})
})

app.delete('/students/:id', (req,res) => {
      const id = Number(req.params.id)

    const student = findStudentById(id)

    if(!student) {
      return res.status(404).json({message: 'Student Not Found'})
    }

    students= students.filter((s) => s.id!==id)
    res.status(200).json({message: 'Student Deleted successfully'})

})

app.listen(PORT,()=> {
  console.log(`Server currently running at http://localhost:${PORT}`)
})