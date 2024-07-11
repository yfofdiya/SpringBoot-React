import React, { useState, useEffect } from 'react';

const HouseModal = ({ isOpen, onClose, onSubmit, address }) => {
  const [newAddress, setNewAddress] = useState('');


  useEffect(() => {
    setNewAddress(address);
  }, [address]);

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit(newAddress);
    setNewAddress('');
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center z-50">
      <div className="bg-white p-8 rounded-lg shadow-lg w-11/12 sm:w-1/2 md:w-1/3">
        <h2 className="text-xl mb-4">{address ? 'Update' : 'Add New'} House</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label htmlFor="address" className="block text-gray-700">
              House Address
            </label>
            <textarea
              id="address"
              name="address"
              value={newAddress}
              onChange={(e) => setNewAddress(e.target.value)}
              className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
              required
            ></textarea>
          </div>
          <div className="flex justify-end">
            <button type="button" onClick={onClose} className="bg-gray-500 text-white py-2 px-4 rounded-lg-lg mr-2">
              Close
            </button>
            <button type="submit" className="bg-blue-500 text-white py-2 px-4 rounded-lg">
              {address ? 'Update' : 'Add'}
            </button>
            {address && (
              <button
                type="button"
                onClick={() => {
                  setNewAddress(address);
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

export default HouseModal;
