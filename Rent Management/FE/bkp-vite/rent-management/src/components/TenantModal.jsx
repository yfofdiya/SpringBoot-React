import React, { useState, useEffect } from 'react';

const TenantModal = ({ isOpen, onClose, onSubmit, tenant }) => {
  const [tenantData, setTenantData] = useState({
    tenantName: '',
    tenantEmail: '',
    mobileNumber: '',
    permanentAddress: '',
    presentAddress: '',
    dateOfBirth: '',
    idProofName: '',
    idProofNumber: '',
    tenantStartDate: '',
    tenantLive: false,
    tenantEndDate: ''
  });

  useEffect(() => {
    if (tenant) {
      setTenantData(tenant);
    } else {
      setTenantData({
        tenantName: '',
        tenantEmail: '',
        mobileNumber: '',
        permanentAddress: '',
        presentAddress: '',
        dateOfBirth: '',
        idProofName: '',
        idProofNumber: '',
        tenantStartDate: '',
        tenantLive: false,
        tenantEndDate: ''
      });
    }
  }, [tenant]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setTenantData({
      ...tenantData,
      [name]: value
    });
  };

  const handleToggleChange = () => {
    setTenantData({
      ...tenantData,
      tenantLive: !tenantData.tenantLive
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit(tenantData);
    onClose();
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center z-50">
      <div className="bg-white p-8 rounded-lg shadow-lg w-11/12 sm:w-1/2 md:w-1/3">
        <h2 className="text-xl mb-4">{tenant ? 'Update Tenant' : 'Add New Tenant'}</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label htmlFor="tenantName" className="block text-gray-700">Name</label>
            <input
              id="tenantName"
              name="tenantName"
              value={tenantData.tenantName}
              onChange={handleChange}
              className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
              required
            />
          </div>
          <div className="flex mb-4">
            <div className="w-1/2 pr-2">
              <label htmlFor="tenantEmail" className="block text-gray-700">Email</label>
              <input
                id="tenantEmail"
                name="tenantEmail"
                type="email"
                value={tenantData.tenantEmail}
                onChange={handleChange}
                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                required
              />
            </div>
            <div className="w-1/2 pl-2">
              <label htmlFor="mobileNumber" className="block text-gray-700">Mobile Number</label>
              <input
                id="mobileNumber"
                name="mobileNumber"
                value={tenantData.mobileNumber}
                onChange={handleChange}
                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                required
              />
            </div>
          </div>
          <div className="flex mb-4">
            <div className="w-1/2 pr-2">
              <label htmlFor="dateOfBirth" className="block text-gray-700">Date of Birth</label>
              <input
                id="dateOfBirth"
                name="dateOfBirth"
                type="date"
                value={tenantData.dateOfBirth}
                onChange={handleChange}
                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                required
              />
            </div>
            <div className="w-1/2 pl-2">
              <label htmlFor="idProofName" className="block text-gray-700">ID Proof Name</label>
              <input
                id="idProofName"
                name="idProofName"
                value={tenantData.idProofName}
                onChange={handleChange}
                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                required
              />
            </div>
          </div>
          <div className="flex mb-4">
            <div className="w-1/2 pr-2">
              <label htmlFor="idProofNumber" className="block text-gray-700">ID Proof Number</label>
              <input
                id="idProofNumber"
                name="idProofNumber"
                value={tenantData.idProofNumber}
                onChange={handleChange}
                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                required
              />
            </div>
            <div className="w-1/2 pl-2">
              <label htmlFor="tenantStartDate" className="block text-gray-700">Start Date</label>
              <input
                id="tenantStartDate"
                name="tenantStartDate"
                type="date"
                value={tenantData.tenantStartDate}
                onChange={handleChange}
                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                required
              />
            </div>
          </div>
          <div className="flex mb-4">
            <div className="w-1/2 pr-2">
              <label htmlFor="permanentAddress" className="block text-gray-700">Permanent Address</label>
              <textarea
                id="permanentAddress"
                name="permanentAddress"
                value={tenantData.permanentAddress}
                onChange={handleChange}
                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                required
              ></textarea>
            </div>
            <div className="w-1/2 pl-2">
              <label htmlFor="presentAddress" className="block text-gray-700">Present Address</label>
              <textarea
                id="presentAddress"
                name="presentAddress"
                value={tenantData.presentAddress}
                onChange={handleChange}
                className="shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500"
                required
              ></textarea>
            </div>
          </div>
          <div className="mb-4">
            <label htmlFor="tenantLive" className="block text-gray-700">Tenant Live?</label>
            <div
              className={`relative inline-flex items-center h-6 rounded-full w-11 cursor-pointer ${tenantData.tenantLive ? 'bg-blue-600' : 'bg-gray-300'
                }`}
              onClick={handleToggleChange}
            >
              <span
                className={`inline-block w-4 h-4 transform bg-white rounded-full transition-transform ${tenantData.tenantLive ? 'translate-x-6' : 'translate-x-1'
                  }`}
              />
            </div>
          </div>
          <div className="mb-4">
            <label htmlFor="tenantEndDate" className="block text-gray-700">End Date</label>
            <input
              id="tenantEndDate"
              name="tenantEndDate"
              type="date"
              value={tenantData.tenantEndDate}
              onChange={handleChange}
              className={`shadow appearance-none border border-gray-400 rounded-lg w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-blue-500 ${tenantData.tenantLive ? 'bg-gray-200' : 'bg-white'}`}
              disabled={tenantData.tenantLive}
            />
          </div>
          <div className="flex justify-end">
            <button type="button" onClick={onClose} className="bg-gray-500 text-white py-2 px-4 rounded-lg mr-2">
              Close
            </button>
            <button type="submit" className="bg-blue-500 text-white py-2 px-4 rounded-lg">
              {tenant ? 'Update' : 'Add'}
            </button>
            {tenant && (
              <button
                type="button"
                onClick={() => {
                  setTenantData(tenant); // Reset form fields to original tenant data
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

export default TenantModal;
