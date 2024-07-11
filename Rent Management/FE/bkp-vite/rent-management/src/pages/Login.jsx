import React, { useState } from 'react'
import { loginUser } from '../services/AuthService';
import { doLogin } from '../utils/auth';
import { useNavigate } from 'react-router-dom';

const Login = () => {

  const [loginData, setLoginData] = useState({
    username: "",
    password: ""
  });

  const navigate = useNavigate();

  const handleChange = (e, field) => {
    setLoginData({
      ...loginData,
      [field]: e.target.value
    })
  };

  const resetLoginData = () => {
    setLoginData({
      username: "",
      password: ""
    })
  };

  const login = (e => {
    e.preventDefault();

    loginUser(loginData)
      .then(response => {
        // save data to local storage
        doLogin(response.data)
        navigate("/all/houses")
        // data saved
        resetLoginData()
      })
      .catch(error => console.log(error))
  });

  return (
    <div className="flex items-center justify-center min-h-screen px-4 md:px-8 lg:px-16 py-8 mt-16 mb-16">
      <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-2xl">
        <h2 className="text-2xl font-bold mb-6 text-center">Login here!</h2>
        <form onSubmit={login}>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="username">
              Username
            </label>
            <input
              className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
              id="username"
              name="username"
              type="text"
              placeholder="Enter username"
              value={loginData.username}
              onChange={e => handleChange(e, "username")}
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password">
              Password
            </label>
            <input
              className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
              id="password"
              name="password"
              type="password"
              placeholder="Enter password"
              value={loginData.password}
              onChange={e => handleChange(e, "password")}
            />
          </div>

          <div className="flex items-center justify-center">
            <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-lg focus:outline-none focus:shadow-outline mr-4" type="submit">
              Login
            </button>
            <button className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded-lg focus:outline-none focus:shadow-outline" onClick={resetLoginData} type="reset">
              Reset
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default Login
