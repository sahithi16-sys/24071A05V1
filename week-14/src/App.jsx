import { useEffect, useState } from 'react'
import './App.css'
import TodoInput from './components/TodoInput';
import TodoList from './components/TodoList';
function App() {

  const [tasks,setTasks] = useState([]);
  const [now, setNow] = useState(new Date());

  useEffect(() => {
    const timer = setInterval(() => {
      setNow(new Date());
    }, 60000);

    return () => clearInterval(timer);
  }, []);

  const addTask = (text, dueDate) => {
    if(!text.trim() || !dueDate) return;

    setTasks([...tasks,{id: Date.now(), text, dueDate, done: false}])
  }

  const toggleTask = (id) => {
    setTasks(tasks.map((t)=> t.id===id?{...t,done: !t.done} : t))
  }

  const updateTask = (id, text, dueDate) => {
    if(!text.trim() || !dueDate) return;

    setTasks(tasks.map((t)=> t.id===id?{...t,text,dueDate} : t))
  }

  const deleteTask = (id) => {
    setTasks(tasks.filter((t) => t.id!==id))
  }

  return (
    <div className = 'todo-app'>
      <h2 className='title' >TodoList App</h2>
    <TodoInput addTask = {addTask}/>
    <TodoList tasks={tasks} toggleTask = {toggleTask} updateTask={updateTask} deleteTask={deleteTask} now={now} />
    </div>
  )
}

export default App
