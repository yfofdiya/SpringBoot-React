import React, { useState, useEffect } from 'react';

const RentModal = ({ isOpen, onClose, onSubmit, rent }) => {
    const [rentData, setRentData] = useState({
        totalRent: '',
        totalPaid: '',
        remainingRentAmount: '',
        rentStartDate: '',
        nextRentPayDate: '',
        lastRentPayDate: ''
    });

    useEffect(() => {
        if (rent) {
            setRentData(rent);
        } else {
            setRentData({
                totalRent: '',
                totalPaid: '',
                remainingRentAmount: '',
                rentStartDate: '',
                nextRentPayDate: '',
                lastRentPayDate: ''
            });
        }
    }, [rent]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setRentData({
            ...rentData,
            [name]: value
        });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        onSubmit(rentData);
        onClose();
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center z-50">
            <div className="bg-white p-8 rounded-lg shadow-lg w-11/12 sm:w-1/2 md:w-1/3">
                <h2 className="text-xl mb-4">Add New Rent</h2>
                <form onSubmit={handleSubmit}>
                    <div className="flex mb-4">
                        <div className="w-1/2 pr-2">
                            <label htmlFor="totalRent" className="block text-gray-700">Total Rent</label>
                            <input
                                id="totalRent"
                                name="totalRent"
                                type='number'
                                value={rentData.totalRent}
                                onChange={handleChange}
                                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                                required
                            />
                        </div>
                        <div className="w-1/2 pr-2">
                            <label htmlFor="totalPaid" className="block text-gray-700">Total Paid</label>
                            <input
                                id="totalPaid"
                                name="totalPaid"
                                type='number'
                                value={rentData.totalPaid}
                                onChange={handleChange}
                                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                                required
                            />
                        </div>
                        </div>
                    <div className="flex mb-4">
                    <div className="w-1/2 pr-2">
                            <label htmlFor="remainingRentAmount" className="block text-gray-700">Total Remaining Rent</label>
                            <input
                                id="remainingRentAmount"
                                name="remainingRentAmount"
                                type='number'
                                value={rentData.remainingRentAmount}
                                onChange={handleChange}
                                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                                required
                            />
                        </div>
                        <div className="w-1/2 pr-2">
                            <label htmlFor="rentStartDate" className="block text-gray-700">Start Date</label>
                            <input
                                id="rentStartDate"
                                name="rentStartDate"
                                type="date"
                                value={rentData.rentStartDate}
                                onChange={handleChange}
                                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                                required
                            />
                        </div>
                    </div>
                    <div className="flex mb-4">
                        <div className="w-1/2 pr-2">
                            <label htmlFor="nextRentPayDate" className="block text-gray-700">Next Rent Pay Date</label>
                            <input
                                id="nextRentPayDate"
                                name="nextRentPayDate"
                                type="text"
                                value={rentData.nextRentPayDate}
                                onChange={handleChange}
                                className='shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500 bg-gray-200'
                                disabled
                            />
                        </div>
                        <div className="w-1/2 pr-2">
                            <label htmlFor="lastRentPayDate" className="block text-gray-700">Last Rent Pay Date</label>
                            <input
                                id="lastRentPayDate"
                                name="lastRentPayDate"
                                type="text"
                                value={rentData.lastRentPayDate}
                                onChange={handleChange}
                                className='shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500 bg-gray-200'
                                disabled
                            />
                        </div>
                    </div>
                    <div className="flex justify-center">
                        <button type="button" onClick={onClose} className="bg-gray-500 text-white py-2 px-4 rounded-lg mr-2">
                            Close
                        </button>
                        <button type="submit" className="bg-blue-500 text-white py-2 px-4 rounded-lg">
                            Add
                        </button>
                        {rent && (
                            <button
                                type="button"
                                onClick={() => {
                                    setRentData(rent);
                                    onClose();
                                }}
                                className="bg-red-500 text-white py-2 px-4 rounded-lg ml-2"
                            >
                                Reset
                            </button>
                        )}
                    </div>
                </form>
            </div>
        </div>
    );
};

export default RentModal;
