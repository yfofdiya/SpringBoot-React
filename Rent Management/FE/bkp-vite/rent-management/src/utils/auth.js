// Check Authentication - isLoggedIn() => Check if token is present in local storage
export const isLoggedIn = () => {
    let data = localStorage.getItem("data")
    return data == null ? false : true
}

// doLogin() => Set data into local storage
export const doLogin = (data) => {
    localStorage.setItem("data", JSON.stringify(data))
}

// doLogout() => Remove data from local storage
export const doLogout = () => {
    localStorage.removeItem("data")
}

// getCurrentLoggedInUser() => Get data from local storage
export const getCurrentLoggedInUser = () => isLoggedIn() ? JSON.parse(localStorage.getItem("data")).ownerName : undefined

//getToken() => Get token from local storage
export const getToken = () => isLoggedIn() ? JSON.parse(localStorage.getItem("data")).token : undefined