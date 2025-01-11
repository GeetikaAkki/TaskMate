import axios from 'axios';
const BASE_URL='http://localhost:8080/api/todos';
export const getAllTodos=()=>axios.get(BASE_URL);
export const saveTodo=(todo)=>axios.post(BASE_URL, todo)
