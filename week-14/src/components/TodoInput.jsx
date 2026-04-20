import { useState } from 'react';
function TodoInput({addTask}) {

    const [text,setText] = useState("");
    const [dueDate,setDueDate] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault()
        addTask(text, dueDate)
        setText("");
        setDueDate("");
    }
  return (
    <div className='task-input'>
        <form onSubmit={handleSubmit}>
            <input value={text} className='text-input' onChange={(e)=>setText(e.target.value)} placeholder='Enter Task' required/>
            <input value={dueDate} className='date-input' type='date' onChange={(e)=>setDueDate(e.target.value)} required/>
            <button className="btn btn-primary add-btn">Add Task</button>
        </form>
    </div>
  )
}

export default TodoInput
