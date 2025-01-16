import axios from "axios";
import { getToken } from "./AuthService";

const BASE_REST_API_URL = 'http://localhost:8080/api/todos';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api'
});

axiosInstance.interceptors.request.use(
    (config) => {
        const token = getToken();
        if (token) {
           
            config.headers.Authorization = token.startsWith('Basic ') ? token : `Basic ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);


export const getAllTodos = () => {
    return axiosInstance.get('/todos');
}



export const saveTodo = (todo) => {
    return axiosInstance.post('/todos', todo);
}

export const getTodo = (id) => {
    return axiosInstance.get(`/todos/${id}`);
}

export const updateTodo = (id, todo) => {
    return axiosInstance.put(`/todos/${id}`, todo);
}

export const deleteTodo = (id) => {
    return axiosInstance.delete(`/todos/${id}`);
}

export const completeTodo = (id) => {
    return axiosInstance.patch(`/todos/${id}/complete`);
}

export const inCompleteTodo = (id) => {
    return axiosInstance.patch(`/todos/${id}/in-complete`);
}