import axios from "axios";
import { getToken } from "../utils/auth"

const url = 'http://localhost:9093/api/rents';

const token = getToken()

export const getAllRentsByTenantId = async (tenantId) => axios.get(url + `/tenant/${tenantId}`, {
    headers: {
        Authorization: `Bearer ${token}`
    }
})

export const addRent = async (tenantId, rentData) => {
    const dataWithTenantId = { ...rentData, tenantId };
    await axios.post(url, dataWithTenantId, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
};

export const updateRent = async (rentId, rentData) => await axios.put(url + `/${rentId}`, rentData, {
    headers: {
        Authorization: `Bearer ${token}`
    }
});

export const deleteRent = async (rentId) => await axios.delete(url + `/${rentId}`, {
    headers: {
        Authorization: `Bearer ${token}`
    }
});