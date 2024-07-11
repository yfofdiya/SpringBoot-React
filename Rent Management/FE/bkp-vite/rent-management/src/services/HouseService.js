import axios from "axios";
import { getToken } from "../utils/auth"

const url = 'http://localhost:9093/api/houses';

const token = getToken()

export const getAllHouses = async () => await axios.get(url, {
    headers: {
        Authorization: `Bearer ${token}`
    }
});

export const addNewHouse = async (houseData) => await axios.post(url, houseData, {
    headers: {
        Authorization: `Bearer ${token}`
    }
});

export const updateHouse = async (houseId, houseData) => await axios.put(url + `/${houseId}`, houseData, {
    headers: {
        Authorization: `Bearer ${token}`
    }
});

export const deleteHouse = async (houseId) => await axios.delete(url + `/${houseId}`, {
    headers: {
        Authorization: `Bearer ${token}`
    }
});