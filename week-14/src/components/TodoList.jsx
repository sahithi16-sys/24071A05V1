import TodoItem from './TodoItem'
function TodoList({tasks,toggleTask,updateTask,deleteTask,now}) {
  return (
    <div className='list'>
        {
            tasks.map((t) => (
                <TodoItem key={t.id} task = {t} toggleTask={toggleTask} updateTask={updateTask} deleteTask={deleteTask} now={now} />
            ))
        }
    </div>
  )
}

export default TodoList
