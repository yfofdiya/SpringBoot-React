import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { addNewHouse, getAllHouses, updateHouse, deleteHouse } from '../services/HouseService';
import HouseModal from '../components/HouseModal';

const AllHouses = () => {
  const [houses, setHouses] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedHouse, setSelectedHouse] = useState(null);

  useEffect(() => {
    const fetchHouses = async () => {
      try {
        const response = await getAllHouses();
        setHouses(response.data.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchHouses();
  }, []);

  const addHouse = async (address) => {
    try {
      const response = await addNewHouse({ address });
      const newHouse = response.data.data;
      setHouses([...houses, newHouse]);
      setIsModalOpen(false);
    } catch (error) {
      console.error('Error adding house:', error);
    }
  };

  const updateHouseData = async (address) => {
    try {
      await updateHouse(selectedHouse.houseId, { address });
      const updatedHouses = houses.map((house) =>
        house.houseId === selectedHouse.houseId ? { ...house, address } : house
      );
      setHouses(updatedHouses);
      setIsModalOpen(false);
    } catch (error) {
      console.error('Error updating house:', error);
    }
  };

  const deleteHouseData = async (houseId) => {
    try {
      await deleteHouse(houseId);
      const updatedHouses = houses.filter((house) => house.houseId !== houseId);
      setHouses(updatedHouses);
    } catch (error) {
      console.error('Error deleting house:', error);
    }
  };

  const openModalForUpdate = (house) => {
    setSelectedHouse(house);
    setIsModalOpen(true);
  };

  const handleDelete = async (houseId) => {
    const confirmDelete = window.confirm('Are you sure you want to delete this house?');
    if (confirmDelete) {
      await deleteHouseData(houseId);
    }
  };

  return (
    <div className="pt-20 pb-16 min-h-screen flex flex-col items-center">
      <div className="w-full max-w-3xl p-4">
        <div className="flex flex-col sm:flex-row justify-between items-center mb-4">
          <h1 className="text-white text-2xl font-bold mb-4 sm:mb-0">Your total Houses : {houses.length}</h1>
          <button
            className="bg-blue-500 text-white py-2 px-4 rounded-lg"
            onClick={() => setIsModalOpen(true)}
          >
            Add House
          </button>
        </div>
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white rounded-lg shadow overflow-hidden">
            <thead>
              <tr className="bg-gray-200 text-left">
                <th className="px-4 py-2">Address</th>
                <th className="px-4 py-2">Actions</th>
                <th className="px-4 py-2">Tenants</th>
              </tr>
            </thead>
            <tbody>
              {houses.map((house, index) => (
                <tr key={house.houseId} className="border-t">
                  <td className="px-4 py-2">{house.address}</td>
                  <td className="px-4 py-2">
                    <button
                      className="bg-green-500 text-white py-1 px-3 rounded-lg mr-2 mb-1"
                      onClick={() => openModalForUpdate(house)}
                    >
                      Update
                    </button>
                    <button
                      className="bg-red-500 text-white py-1 px-3 rounded-lg"
                      onClick={() => handleDelete(house.houseId)}
                    >
                      Delete
                    </button>
                  </td>
                  <td className="px-4 py-2">
                    <Link
                      to={`/house/${house.houseId}/all/tenants`} 
                      className="bg-blue-500 text-white py-2 px-4 rounded-lg text-lg font-semibold"
                    >
                      View
                    </Link>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      <HouseModal
        isOpen={isModalOpen}
        onClose={() => {
          setIsModalOpen(false);
          setSelectedHouse(null);
        }}
        onSubmit={selectedHouse ? updateHouseData : addHouse}
        address={selectedHouse ? selectedHouse.address : ''}
      />
    </div>
  );
};

export default AllHouses;
