const cityForm = document.getElementById('cityForm');
const cityInput = document.getElementById('cityInput');
const weatherData = document.getElementById('weatherData');

cityForm.addEventListener('submit', (event) => {
  event.preventDefault();

  const city = cityInput.value;
  // Replace with your OpenWeatherMap API key
  const apiKey = '811c58cffcdcbb85dad52cc5b324ded0';

  // API request
  fetch(`https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${apiKey}&units=metric`)
    .then(response => response.json())
    .then(data => {
      // Process and display weather data
      const cityName = data.city.name;

      const uniqueDates = [];
      const uniqueWeatherData = [];

      data.list.forEach(item => {
        const date = new Date(item.dt_txt).toLocaleDateString('en-US', { weekday: 'long', month: 'short', day: 'numeric' });

        // Check if the date is not already in the uniqueDates array
        if (!uniqueDates.includes(date) && uniqueDates.length < 3) {
          uniqueDates.push(date);

          const highTemp = item.main.temp_max;
          const lowTemp = item.main.temp_min;
          const isRainPredicted = item.weather.some(w => w.main === 'Rain');
          const isHighTemp = highTemp > 40;

          // Construct an object with all fields
          const weatherEntry = {
            date: date,
            highTemperature: highTemp,
            lowTemperature: lowTemp,
            carryUmbrella: isRainPredicted,
            useSunscreen: isHighTemp,
          };

          // Add the entry to the list
          uniqueWeatherData.push(weatherEntry);
        }
      });

      let html = '';
      uniqueWeatherData.forEach(item => {
        html += `
          <div class="weather-card">
            <h3>${item.date}</h3>
            <p>High: ${item.highTemperature}°C</p>
            <p>Low: ${item.lowTemperature}°C</p>
            ${item.carryUmbrella ? '<p class="reminder">Carry an umbrella</p>' : ''}
            ${item.useSunscreen ? '<p class="reminder">Use sunscreen lotion</p>' : ''}
          </div>
        `;
      });

      // Display the weather data in the UI
      weatherData.innerHTML = html;

      // Prepare the data to send to the server
      const postData = {
        city: cityName,
        weatherCondition: '', // Set the weather condition here
        highTemperature: '', // Set the high temperature here
        lowTemperature: '', // Set the low temperature here
        carryUmbrella: false, // Set the carry umbrella value here
        useSunscreen: false, // Set the use sunscreen value here
        weatherEntries: uniqueWeatherData,
      };

      // Send the data to the server using a POST request
      fetch('http://localhost:8080/api/weather/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(postData),
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then(serverResponse => {
          console.log('Server Response:', serverResponse);
        })
        .catch(error => {
          console.error('Error sending data to the server:', error);
        });
    })
    .catch(error => {
      console.error('Error:', error);
      weatherData.innerHTML = 'Error occurred while fetching weather data.';
    });
});









