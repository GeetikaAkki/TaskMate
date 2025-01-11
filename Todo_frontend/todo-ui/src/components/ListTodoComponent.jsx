import React, { useEffect, useState } from 'react'
import { getAllTodos } from '../services/TodoService'
import {useNavigate} from 'react-router-dom'



const ListTodoComponent = () => {
    useEffect(()=>{
        listTodos();



    },
    []

    )
    const navigate=useNavigate()
    function listTodos(){
        getAllTodos().then((response) => {
            setTodos(response.data);
        }).catch(error => {
            console.error(error);
        })
    }
    const [todos,setTodos]=useState([])

    function addNewTodo(){
        navigate('/add-todo')

    }

  return (
    <div className='container'>
        <h2 className='text-center'>List of Tasks</h2>
        <button className='btn btn-primary mb-2' onClick={addNewTodo}>Add Task</button>
        
        <div>
            <table className='table table-bordered table-striped'>
                <thead>
                    <tr>
                        <th>Task Title</th>
                        <th>Task Description</th>
                        <th>Task Completed</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        todos.map(todo => 
                            <tr key={todo.id}>
                                <td>{todo.title}</td>
                                <td>{todo.description}</td>
                                <td>{todo.completed ? 'YES': 'NO'}</td>
                               
                            </tr>
                        )
                    }

                </tbody>
            </table>
        </div>

    </div>
  )
}

export default ListTodoComponent