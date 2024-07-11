import axios from "axios";
import { getToken } from "../utils/auth"

const url = 'http://localhost:9093/api/tenants';

const token = getToken()

export const getAllTenantsByHouseId = async (houseId) => axios.get(url + `/house/${houseId}`, {
    headers: {
        Authorization: `Bearer ${token}`
    }
})

export const addTenant = async (houseId, tenantData) => {
    const dataWithHouseId = { ...tenantData, houseId };
    await axios.post(url, dataWithHouseId, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
};

export const updateTenant = async (tenantId, tenantData) => await axios.put(url + `/${tenantId}`, tenantData, {
    headers: {
        Authorization: `Bearer ${token}`
    }
});

export const deleteTenant = async (tenantId) => await axios.delete(url + `/${tenantId}`, {
    headers: {
        Authorization: `Bearer ${token}`
    }
});