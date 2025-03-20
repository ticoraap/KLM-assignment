import './App.css'
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { useEffect, useState } from 'react';
import { formatDate } from 'date-fns/format';

interface Holiday {
  holidayId: string;
  holidayLabel: string;
  employeeId: string;
  startOfHoliday: string;
  endOfHoliday: string;
  status: string;
}

function App() {
  const [entries, setEntries] = useState<Holiday[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  const fetchHolidays = async () => {
    setLoading(true);
    setError(null);
    try {
      
      const response = await fetch("http://localhost:8080/holidays");
      if (!response.ok) throw new Error("Failed to fetch holidays");
      const data: Holiday[] = await response.json();
      setEntries(data);

    } catch (err) {

      setError((err as Error).message);

    } finally {

      setLoading(false);

    }
  };

  useEffect(() => {
    fetchHolidays();
  },[])
  return (
    <div>
      <h2>Holidays</h2>
      {loading && <p>Loeading holidays...</p>}
      <DataTable value={entries} >
        <Column field="holidayId" header="Holiday ID"></Column>
        <Column field="holidayLabel" header="Label"></Column>
        <Column field="employeeId" header="Employee ID"></Column>
        <Column field="startOfHoliday" header="Start Date" body={(rowData) => formatDate(rowData.startOfHoliday, "dd MMM yyyy, HH:mm")}></Column>
        <Column field="endOfHoliday" header="End Date" body={(rowData) => formatDate(rowData.endOfHoliday, "dd MMM yyyy, HH:mm")}></Column>
        <Column field="status" header="Status"></Column>
      </DataTable>
    </div>
  );
}

export default App
