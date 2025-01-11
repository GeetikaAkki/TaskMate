import React from 'react'
import {useState} from 'react'
import { saveTodo } from '../services/TodoService'
import { useNavigate } from 'react-router-dom'

const TodoComponent = () => {


   const [title, setTitle]= useState('')
   const [description, setDescription]=useState('')
   const [completed, setCompleted]=useState(false)
   const navigate=useNavigate()
   function saveOrUpdateTodo(e){
    e.preventDefault()
    const todo={title, description, completed}
    console.log(todo);
    saveTodo(todo).then((response)=>{
        console.log(response.data)
        navigate('/todos')
    }).catch(error=>{
        console.error(error);
    })
   }
  return (
   <div className='container'>
    <br></br>
    <div className='row'>
    <div className='card col-md-6 offset-md-3 offset-md-3'>
        < h2 className='text-center'>Add Task</h2>
        <div className='card-body'>
            <form>
                <div className='form-group mb-2'>
                    <label className='form-label'>Task Title: </label>
                    <input
                    className='form-control'
                     type='text'
                     placeholder='Enter Task Title'
                     name='title'
                     value={title} 
                     onChange={(e)=>setTitle(e.target.value)}

                    
                    >
                    </input>

                </div>
                <div className='form-group mb-2'>
                    <label className='form-label'>Task Description: </label>
                    
                    <input
                    className='form-control'
                     type='text'
                     placeholder='Enter Task Description'
                     name='description'
                     value={description} 
                     onChange={(e)=>setDescription(e.target.value)}

                    
                    >
                    </input>

                </div>
                <div className='form-group mb-2'>
                    <label className='form-label'>Task Completed: </label>
                    <select
                    className='form-control'
                    value={completed}
                    onChange={(e)=>setCompleted(e.target.value)}
                    >
                        <option value="false">No</option>
                        <option value="true">Yes</option>



                    </select>

                </div>
                <button className='btn btn-success' onClick={(e)=>saveOrUpdateTodo(e)}>Submit</button>
            </form>

        </div>


    </div>

    </div>

   </div>
    
  )
}
export default TodoComponent