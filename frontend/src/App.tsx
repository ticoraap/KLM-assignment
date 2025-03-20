import './App.css'
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Calendar } from 'primereact/calendar';
import { useEffect, useState } from 'react';
import { formatDate } from 'date-fns/format';
import { Nullable } from 'primereact/ts-helpers';
import { Button } from 'primereact/button';

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
  const [feedback, setFeedback] = useState<string | null>(null);
  const [dates, setDates ] = useState<Nullable<(Date | null)[]>>(null);
  const endpoint = "http://localhost:8080/holidays"

  const fetchHolidays = async () => {
    setLoading(true);
    try {
      
      const response = await fetch(endpoint);
      if (!response.ok) throw new Error("Failed to fetch holidays");
      const data: Holiday[] = await response.json();
      setEntries(data);

    } catch (err) {

      setFeedback((err as Error).message);

    } finally {

      setLoading(false);

    }
  };

  const submitHoliday = async () => {
    if (!(dates && dates.length === 2)) return;

    const holiday = {
      holidayLabel: "Test holiday",
      employeeId: "KLM012345",
      startOfHoliday: dates[0]?.toISOString() || "",
      endOfHoliday: dates[1]?.toISOString() || "",
      status: "REQUESTED",
    } 

    try{
      const response = await fetch(endpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(holiday)
      })
      if (!response.ok){
        const errorData = await response.json();
        setFeedback(errorData.message || "unknown error")
        throw new Error(errorData.mesage)
      } else {
        setFeedback("Holiday sucessfully added")
        fetchHolidays();
      }
    } catch {

    }

  }

  useEffect(() => {
    fetchHolidays();
  },[])
  return (
    <div>
      <h2>Holidays</h2>
      <div>
        <Calendar showTime value={dates} onChange={(e) => setDates(e.value)} selectionMode='range' readOnlyInput hideOnRangeSelection/>
        <Button onClick={submitHoliday} disabled={!dates} label='Submit new Holiday'/>
        <p>{feedback}</p>
      </div>
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
