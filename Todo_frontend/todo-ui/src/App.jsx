
import './App.css'
import ListTodoComponent from './components/ListTodoComponent';
import HeaderComponent from './components/HeaderComponent';
import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import TodoComponent from './components/TodoComponent';
import RegisterComponent from './components/RegisterComponent';
import LoginComponent from './components/LoginComponent';
import { isUserLoggedIn } from './services/AuthService';



function App() {
  function AuthenticatedRoute({children}){
    const isAuth=isUserLoggedIn();
    if(isAuth){
      return children;
    }
    else{
      return <Navigate to="/"/>
    }
  }
  
  

  return (
    <>
      
       <BrowserRouter>
       <HeaderComponent/>
        <Routes>

          <Route path='/' element={<LoginComponent/>}> 

          </Route>
          <Route path='/todos' element={
            <AuthenticatedRoute>
            <ListTodoComponent/>
            </AuthenticatedRoute> 

          }></Route>
           <Route path='/add-todo' element={
            <AuthenticatedRoute>
            <TodoComponent/>
            </AuthenticatedRoute>
            }> </Route>
           <Route path='/update-todo/:id' element={<TodoComponent/>}></Route>

           <Route path='/register' element={<RegisterComponent/>}></Route>
           <Route path='/login' element={<LoginComponent/>}></Route>
        </Routes>
      
       
       </BrowserRouter>
          
             
    </>
  )
}

export default App
