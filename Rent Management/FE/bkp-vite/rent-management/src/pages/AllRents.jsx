import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { getAllRentsByTenantId, addRent, updateRent, deleteRent } from '../services/RentService';
import RentModal from '../components/RentModal';
import { Link } from 'react-router-dom';

const AllRents = () => {
    const { tenantId } = useParams();
    const [rents, setRents] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedRent, setSelectedRent] = useState(null);

    useEffect(() => {
        const fetchRents = async () => {
            try {
                const response = await getAllRentsByTenantId(tenantId);
                setRents(response.data.data);
            } catch (error) {
                console.error('Error fetching rents:', error);
            }
        };
        fetchRents();
    }, []);

    const handleAddRent = async (rentData) => {
        try {
            const response = await addRent(tenantId, rentData);
            const newRent = response.data.data;
            setRents([...rents, newRent]);
            setIsModalOpen(false);
        } catch (error) {
            console.error('Error adding rent:', error);
        }
    };

    const deleteRentData = async (rentId) => {
        try {
            await deleteRent(rentId);
            const updatedRents = rents.filter((rent) => rent.rentId !== rentId);
            setRents(updatedRents);
        } catch (error) {
            console.error('Error deleting rent:', error);
        }
    };

    const handleDelete = async (rentId) => {
        const confirmDelete = window.confirm('Are you sure you want to delete this rent?');
        if (confirmDelete) {
            await deleteRentData(rentId);
        }
    };

    return (
        <div className="pt-20 pb-16 min-h-screen flex flex-col items-center bg-gray-900">
            <div className="w-full p-4">
                <div className="flex flex-col sm:flex-row justify-between items-center mb-4">
                    <h1 className="text-white text-2xl font-bold mb-4 sm:mb-0">Total Rent Paid: {rents.length}</h1>
                    <button
                        className="bg-blue-500 text-white py-2 px-4 rounded-lg"
                        onClick={() => setIsModalOpen(true)}
                    >
                        Add Rent
                    </button>
                </div>
                <div className="overflow-x-auto w-full">
                    <table className="min-w-full bg-white rounded-lg shadow overflow-hidden">
                        <thead>
                            <tr className="bg-gray-200 text-left">
                                <th className="px-4 py-2">Total Rent</th>
                                <th className="px-4 py-2">Total Paid</th>
                                <th className="px-4 py-2">Remaining Rent</th>
                                <th className="px-4 py-2">Start Date</th>
                                <th className="px-4 py-2">Next Rent Pay Date</th>
                                <th className="px-4 py-2">Last Rent Paid Date</th>
                                <th className="px-4 py-2">Actions</th>
                                <th className="px-4 py-2">Details</th>
                            </tr>
                        </thead>
                        <tbody>
                            {rents.map((rent) => (
                                <tr key={rent.rentId} className="border-t">
                                    <td className="px-4 py-2">{rent.totalRent}</td>
                                    <td className="px-4 py-2">{rent.totalPaid}</td>
                                    <td className="px-4 py-2">{rent.remainingRentAmount}</td>
                                    <td className="px-4 py-2">{rent.rentStartDate}</td>
                                    <td className="px-4 py-2">{rent.nextRentPayDate}</td>
                                    <td className="px-4 py-2">{rent.lastRentPayDate}</td>
                                    <td className="px-4 py-2">
                                        <button
                                            className="bg-red-500 text-white py-1 px-3 rounded-lg"
                                            onClick={() => handleDelete(rent.rentId)}
                                        >
                                            Delete
                                        </button>
                                    </td>
                                    <td className="px-4 py-2">
                                        <Link
                                            to={`/rent/${rent.rentId}`}
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
            <RentModal
                isOpen={isModalOpen}
                onClose={() => {
                    setIsModalOpen(false);
                    setSelectedRent(null);
                }}
                onSubmit={handleAddRent}
                rent={selectedRent}
            />
        </div>
    );
};

export default AllRents;
