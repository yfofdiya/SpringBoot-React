import axios from "axios";

const BASE_URL = 'http://localhost:9091/api/employees';

export const listAllEmployees = () => axios.get(BASE_URL);

export const createEmployee = (employee) => axios.post(BASE_URL, employee)

export const employeeById = (id) => axios.get(BASE_URL + '/' + id);

export const updateEmployee = (id, employee) => axios.put(BASE_URL + '/' + id, employee)

export const deleteById = (id) => axios.delete(BASE_URL + '/' + id);