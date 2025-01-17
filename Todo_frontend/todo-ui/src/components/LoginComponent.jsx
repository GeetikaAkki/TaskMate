import React , {useState}from 'react'
import { loginAPICall, saveLoggedInUser, storeToken } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';
const LoginComponent = () => {
    const [usernameOrEmail, setUsernameOrEmail]=useState('')
    const [password, setPassword]=useState('')
    const navigator=useNavigate();
    const handleLoginForm = async (e) => {
        e.preventDefault();
        try {
            const response = await loginAPICall(usernameOrEmail, password);
            
           // const token = 'Basic ' + window.btoa(`${usernameOrEmail}:${password}`);
           const token='Bearer ' +response.data.accessToken;
            console.log(token);
            storeToken(token);
            saveLoggedInUser(usernameOrEmail);
            navigator("/todos");
            window.location.reload(false);
        } catch (error) {
            console.error(error);
        }
    }
    return (
        <div className='container'>
            <br /> <br />
            <div className='row'>
                <div className='col-md-6 offset-md-3'>
                    <div className='card'>
                        <div className='card-header'>
                            <h2 className='text-center'> Login Form </h2>
                        </div>
    
                        <div className='card-body'>
                            <form>
    
                                <div className='row mb-3'>
                                    <label className='col-md-3 control-label'> Username or Email</label>
                                    <div className='col-md-9'>
                                        <input
                                            type='text'
                                            name='username'
                                            className='form-control'
                                            placeholder='Enter username'
                                            value={usernameOrEmail}
                                            onChange={ (e) => setUsernameOrEmail(e.target.value)}
                                        >
                                        </input>
                                    </div>
                                </div>
    
                                <div className='row mb-3'>
                                    <label className='col-md-3 control-label'> Password </label>
                                    <div className='col-md-9'>
                                        <input
                                            type='password'
                                            name='password'
                                            className='form-control'
                                            placeholder='Enter password'
                                            value={password}
                                            onChange={ (e) => setPassword(e.target.value)}
                                        >
                                        </input>
                                    </div>
                                </div>
    
                                <div className='form-group mb-3'>
                                    <button className='btn btn-primary' onClick={ (e) => handleLoginForm(e)}>Submit</button>
    
                                </div>
                            </form>
    
                        </div>
    
                    </div>
                </div>
            </div>
    
    
        </div>
      )
}

export default LoginComponent