curl -X POST http://127.0.0.1:8100/add-expense \
   -H "Content-Type: application/json" \
   -d '{"expenseDate":"1675193764","description":"Lunch",
	   "location":"Alices Restaurant","amount":45.67}'
