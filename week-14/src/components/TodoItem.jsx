import { useState } from 'react';

function getTimeLeft(dueDate, now) {
  const due = new Date(`${dueDate}T23:59:59`);
  const difference = due.getTime() - now.getTime();

  if (difference <= 0) {
    return 'Time is up';
  }

  const totalHours = Math.floor(difference / (1000 * 60 * 60));
  const days = Math.floor(totalHours / 24);
  const hours = totalHours % 24;

  return `${days}d ${hours}hr left`;
}

function formatDate(dateValue) {
  return new Date(`${dateValue}T00:00:00`).toLocaleDateString();
}

function TodoItem({task,toggleTask,updateTask,deleteTask,now}) {
  const [isEditing, setIsEditing] = useState(false);
  const [editedText, setEditedText] = useState(task.text);
  const [editedDate, setEditedDate] = useState(task.dueDate);

  const handleUpdate = () => {
    if (!isEditing) {
      setIsEditing(true);
      return;
    }

    if (!editedText.trim() || !editedDate) {
      return;
    }

    updateTask(task.id, editedText, editedDate);
    setIsEditing(false);
  };

  return (
    <div className="row">
        <input type="checkbox" className='status' checked={task.done} onChange={()=>toggleTask(task.id)} />
        <div className='task-details'>
          {isEditing ? (
            <div className='edit-fields'>
              <input value={editedText} className='edit-text-input' onChange={(e)=>setEditedText(e.target.value)} required />
              <input value={editedDate} className='edit-date-input' type='date' onChange={(e)=>setEditedDate(e.target.value)} required />
            </div>
          ) : (
            <>
              <span className={task.done? "done" : "not-done"}>{task.text}</span>
              <small>Due: {formatDate(task.dueDate)} | {getTimeLeft(task.dueDate, now)}</small>
            </>
          )}
        </div>
        <div className='task-actions'>
          <button className='update-btn' onClick={handleUpdate}>{isEditing ? 'Save' : 'Update'}</button>
          <button className='delete-btn' onClick={() => deleteTask(task.id)}>Delete</button>
        </div>
    </div>
  )
}

export default TodoItem
