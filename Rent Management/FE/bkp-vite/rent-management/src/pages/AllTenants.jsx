import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getAllTenantsByHouseId, addTenant, updateTenant, deleteTenant } from '../services/TenantService';
import TenantModal from '../components/TenantModal';
import { Link } from 'react-router-dom';

const AllTenants = () => {
  const { houseId } = useParams();
  const [tenants, setTenants] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedTenant, setSelectedTenant] = useState(null);

  useEffect(() => {
    const fetchTenants = async () => {
      try {
        const response = await getAllTenantsByHouseId(houseId);
        setTenants(response.data.data);
      } catch (error) {
        console.error('Error fetching tenants:', error);
      }
    };
    fetchTenants();
  }, []);

  const handleAddTenant = async (tenantData) => {
    try {
      const response = await addTenant(houseId, tenantData);
      const newTenant = response.data.data;
      setTenants([...tenants, newTenant]);
      setIsModalOpen(false);
    } catch (error) {
      console.error('Error adding tenant:', error);
    }
  };

  const handleUpdateTenant = async (tenantData) => {
    try {
      await updateTenant(selectedTenant.tenantId, tenantData);
      const updatedTenants = tenants.map(tenant =>
        tenant.tenantId === tenantData.tenantId ? tenantData : tenant
      );
      setTenants(updatedTenants);
      setIsModalOpen(false);
      setSelectedTenant(null);
    } catch (error) {
      console.error('Error updating tenant:', error);
    }
  };

  const deleteTenantData = async (tenantId) => {
    try {
      await deleteTenant(tenantId);
      const updatedTenants = tenants.filter((tenant) => tenant.tenantId !== tenantId);
      setTenants(updatedTenants);
    } catch (error) {
      console.error('Error deleting tenant:', error);
    }
  };

  const openModalForUpdate = (tenant) => {
    setSelectedTenant(tenant);
    setIsModalOpen(true);
  };

  const handleDelete = async (tenantId) => {
    const confirmDelete = window.confirm('Are you sure you want to delete this tenant?');
    if (confirmDelete) {
      await deleteTenantData(tenantId);
    }
  };

  return (
    <div className="pt-20 pb-16 min-h-screen flex flex-col items-center bg-gray-900">
      <div className="w-full p-4">
        <div className="flex flex-col sm:flex-row justify-between items-center mb-4">
          <h1 className="text-white text-2xl font-bold mb-4 sm:mb-0">Your total Tenants: {tenants.length}</h1>
          <button
            className="bg-blue-500 text-white py-2 px-4 rounded-lg"
            onClick={() => setIsModalOpen(true)}
          >
            Add Tenant
          </button>
        </div>
        <div className="overflow-x-auto w-full">
          <table className="min-w-full bg-white rounded-lg shadow overflow-hidden">
            <thead>
              <tr className="bg-gray-200 text-left">
                <th className="px-4 py-2">Name</th>
                <th className="px-4 py-2">Email</th>
                <th className="px-4 py-2">Mobile Number</th>
                <th className="px-4 py-2">Permanent Address</th>
                <th className="px-4 py-2">Present Address</th>
                <th className="px-4 py-2">Date of Birth</th>
                <th className="px-4 py-2">ID Proof Name</th>
                <th className="px-4 py-2">ID Proof Number</th>
                <th className="px-4 py-2">Start Date</th>
                <th className="px-4 py-2">Tenant Live?</th>
                <th className="px-4 py-2">End Date</th>
                <th className="px-4 py-2">Actions</th>
                <th className="px-4 py-2">Rents</th>
              </tr>
            </thead>
            <tbody>
              {tenants.map((tenant) => (
                <tr key={tenant.tenantId} className="border-t">
                  <td className="px-4 py-2">{tenant.tenantName}</td>
                  <td className="px-4 py-2">{tenant.tenantEmail}</td>
                  <td className="px-4 py-2">{tenant.mobileNumber}</td>
                  <td className="px-4 py-2">{tenant.permanentAddress}</td>
                  <td className="px-4 py-2">{tenant.presentAddress}</td>
                  <td className="px-4 py-2">{tenant.dateOfBirth}</td>
                  <td className="px-4 py-2">{tenant.idProofName}</td>
                  <td className="px-4 py-2">{tenant.idProofNumber}</td>
                  <td className="px-4 py-2">{tenant.tenantStartDate}</td>
                  <td className="px-4 py-2">{tenant.tenantLive ? 'Yes' : 'No'}</td>
                  <td className="px-4 py-2">{tenant.tenantEndDate}</td>
                  <td className="px-4 py-2">
                    <button
                      className="bg-green-500 text-white py-1 px-3 rounded-lg mr-2 mb-1"
                      onClick={() => openModalForUpdate(tenant)}
                    >
                      Update
                    </button>
                    <button
                      className="bg-red-500 text-white py-1 px-3 rounded-lg"
                      onClick={() => handleDelete(tenant.tenantId)}
                    >
                      Delete
                    </button>
                  </td>
                  <td className="px-4 py-2">
                    <Link
                      to={`/tenant/${tenant.tenantId}/all/rents`}
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
      <TenantModal
        isOpen={isModalOpen}
        onClose={() => {
          setIsModalOpen(false);
          setSelectedTenant(null);
        }}
        onSubmit={selectedTenant ? handleUpdateTenant : handleAddTenant}
        tenant={selectedTenant}
      />
    </div>
  );
};

export default AllTenants;
