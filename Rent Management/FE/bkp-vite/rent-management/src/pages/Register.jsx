import React, { useState } from 'react';
import { createNewOwner } from '../services/AuthService';

const Register = () => {
    const [ownerData, setOwnerData] = useState({
        ownerName: "",
        ownerEmail: "",
        username: "",
        password: "",
        mobileNumber: "",
        dateOfBirth: "",
        address: ""
    });

    const handleChange = (e, field) => {
        setOwnerData({
            ...ownerData,
            [field]: e.target.value
        })
    };

    const resetOwnerData = () => {
        setOwnerData({
            ownerName: "",
            ownerEmail: "",
            username: "",
            password: "",
            mobileNumber: "",
            dateOfBirth: "",
            address: ""
        })
    };

    const createOwner = (e) => {
        e.preventDefault();

        createNewOwner(ownerData)
            .then(response => {
                console.log(response)
                resetOwnerData()
            })
            .catch(error => console.error(error));
    };

    return (
        <div className="flex items-center justify-center min-h-screen px-4 md:px-8 lg:px-16 py-8 mt-16 mb-16">
            <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-2xl">
                <h2 className="text-2xl font-bold mb-6 text-center">Register here!</h2>
                <form onSubmit={createOwner}>
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                        <div>
                            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="name">
                                Owner's Name
                            </label>
                            <input
                                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                                id="name"
                                name="name"
                                type="text"
                                placeholder="Enter owner's name"
                                value={ownerData.ownerName}
                                onChange={e => handleChange(e, "ownerName")}
                            />
                        </div>

                        <div>
                            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="email">
                                Owner's Email
                            </label>
                            <input
                                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                                id="email"
                                name="email"
                                type="email"
                                placeholder="Enter owner's email"
                                value={ownerData.ownerEmail}
                                onChange={e => handleChange(e, "ownerEmail")}
                            />
                        </div>
                    </div>

                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                        <div>
                            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="username">
                                Owner's Username
                            </label>
                            <input
                                className="shadow appearance-none border border-gray-400 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                                id="username"
                                name="username"
                                type="text"
                                placeholder="Enter owner's username"
                                value={ownerData.username}
                                onChange={e => handleChange(e, "username")}
                            />
                        </div>

                        <div>
                            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password">
                                Owner's Password
                            </label>
                            <input
                                className="shadow appearance-none border border-gray-400 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                                id="password"
                                name="password"
                                type="password"
                                placeholder="Enter owner's password"
                                value={ownerData.password}
                                onChange={e => handleChange(e, "password")}
                            />
                        </div>
                    </div>

                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                        <div>
                            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="mobile">
                                Owner's Mobile Number
                            </label>
                            <input
                                className="shadow appearance-none border border-gray-400 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                                id="mobile"
                                name="mobile"
                                type="tel"
                                placeholder="Enter owner's mobile number"
                                value={ownerData.mobileNumber}
                                onChange={e => handleChange(e, "mobileNumber")}
                            />
                        </div>

                        <div>
                            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="dob">
                                Owner's Date of Birth
                            </label>
                            <input
                                className="shadow appearance-none border border-gray-400 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                                id="dob"
                                name="dob"
                                type="date"
                                value={ownerData.dateOfBirth}
                                onChange={e => handleChange(e, "dateOfBirth")}
                            />
                        </div>
                    </div>

                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="address">
                            Owner's Address
                        </label>
                        <textarea
                            className="shadow appearance-none border border-gray-400 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                            id="address"
                            name="address"
                            placeholder="Enter owner's address"
                            value={ownerData.address}
                            onChange={e => handleChange(e, "address")}
                        ></textarea>
                    </div>

                    <div className="flex items-center justify-center">
                        <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline mr-4" type="submit">
                            Register
                        </button>
                        <button className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" onClick={resetOwnerData} type="reset">
                            Reset
                        </button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Register;
