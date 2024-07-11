import axios from "axios";

const url = 'http://localhost:9093/api/auth';

export const createNewOwner = ownerData => axios.post(url + '/sign-up', ownerData);


export const loginUser = (loginData) => axios.post(url + '/login', loginData);