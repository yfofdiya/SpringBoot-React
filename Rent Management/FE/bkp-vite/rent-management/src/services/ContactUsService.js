import axios from "axios";

const url = 'http://localhost:9093/api/contact-us';

export const addNewQuery = async (query) => await axios.post(url, query);