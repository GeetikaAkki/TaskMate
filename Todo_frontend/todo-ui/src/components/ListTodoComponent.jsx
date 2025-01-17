import React, { useEffect, useState } from 'react'
import { completeTodo, deleteTodo, getAllTodos, inCompleteTodo } from '../services/TodoService'
import { useNavigate } from 'react-router-dom'
import { isAdminUser } from '../services/AuthService';

const ListTodoComponent = () => {
    const [todos, setTodos] = useState([]);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const isAdmin=isAdminUser();

    useEffect(() => {
        listTodos();
    }, []);
    
    function listTodos() {
        getAllTodos()
            .then((response) => {
                setTodos(response.data);
                setError(null); 
            })
            .catch(error => {
                console.error(error);
                if (error.response?.status === 401) {
                    // Unauthorized - redirect to login
                    localStorage.removeItem('token');
                    navigate('/login');
                } else {
                    setError('Failed to fetch todos. Please try again.');
                }
            });
    }

    function addNewTodo() {
        navigate('/add-todo');
    }

    function updateTodo(id) {
        navigate(`/update-todo/${id}`);
    }
    
    function removeTodo(id) {
        deleteTodo(id)
            .then((response) => {
                listTodos();
            })
            .catch(error => {
                console.error(error);
               
            });
    }

    function markCompleteTodo(id) {
        completeTodo(id)
            .then((response) => {
                listTodos();
            })
            .catch(error => {
                console.error(error);
                if (error.response?.status === 401) {
                    localStorage.removeItem('token');
                    navigate('/login');
                } else {
                    setError('Failed to mark todo as complete. Please try again.');
                }
            });
    }

    function markInCompleteTodo(id) {
        inCompleteTodo(id)
            .then((response) => {
                listTodos();
            })
            .catch(error => {
                console.error(error);
                if (error.response?.status === 401) {
                    localStorage.removeItem('token');
                    navigate('/login');
                } else {
                    setError('Failed to mark todo as incomplete. Please try again.');
                }
            });
    }

   
  return (
    <div className='container'>
        <h2 className='text-center'>List of Tasks</h2>
        {
            isAdmin &&
            <button className='btn btn-primary mb-2' onClick={addNewTodo}>Add Task</button>
        }
       
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
                                <td>{
                                    isAdmin &&
                                    <button className='btn btn-info' onClick={() => updateTodo(todo.id)}>Update</button>
                                    
                                    }
                                    {
                                     isAdmin &&
                                     <button className='btn btn-danger' onClick={() => removeTodo(todo.id)} style={ { marginLeft: "10px" }} >Delete</button>
                                     }
                                     
                                    <button className='btn btn-success' onClick={() => markCompleteTodo(todo.id)} style={ { marginLeft: "10px" }} >Complete</button>
                                    <button className='btn btn-info' onClick={() => markInCompleteTodo(todo.id)} style={ { marginLeft: "10px" }} >In Complete</button>
                                </td>
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