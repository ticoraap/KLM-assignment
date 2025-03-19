curl -X POST -H "Content-Type: application/json" -d '{
  "holidayLabel": "Newest Holiday yay!",
  "employeeId": "KLM012345",
  "startOfHoliday": "2025-03-10T19:46:50.538790Z",
  "endOfHoliday": "2025-03-13T19:46:50.538791Z",
  "status": "REQUESTED"
}' http://localhost:8080/holidays
