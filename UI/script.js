//const cityForm = document.getElementById('cityForm');
//const cityInput = document.getElementById('cityInput');
//const cityName = document.getElementById('cityName');
//const weatherData = document.getElementById('weatherData');
//
//cityForm.addEventListener('submit', (event) => {
//  event.preventDefault();
//
//  const city = cityInput.value;
//  // Replace with your OpenWeatherMap API key
//  const apiKey = '811c58cffcdcbb85dad52cc5b324ded0';
//
//  // API request
//  fetch(`https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${apiKey}&units=metric`)
//    .then(response => response.json())
//    .then(data => {
//      // Process and display weather data
//      cityName.textContent = data.city.name;
//
//      const uniqueDates = [];
//      const uniqueWeatherData = [];
//      let uniqueDatesCount = 0;
//
//      data.list.forEach(item => {
//        const date = new Date(item.dt_txt).toLocaleDateString('en-US', { weekday: 'long', month: 'short', day: 'numeric' });
//
//        if (!uniqueDates.includes(date) && uniqueDatesCount < 3) {
//          uniqueDates.push(date);
//          uniqueWeatherData.push(item);
//          uniqueDatesCount++;
//        }
//      });
//
//      let html = '';
//      uniqueWeatherData.forEach(item => {
//        const date = new Date(item.dt_txt).toLocaleDateString('en-US', { weekday: 'long', month: 'short', day: 'numeric' });
//        const highTemp = item.main.temp_max;
//        const lowTemp = item.main.temp_min;
//        const isRainPredicted = item.weather.some(w => w.main === 'Rain');
//        const isHighTemp = highTemp > 40;
//
//        html += `
//          <div class="weather-card">
//            <h3>${date}</h3>
//            <p>High: ${highTemp}°C</p>
//            <p>Low: ${lowTemp}°C</p>
//            ${isRainPredicted ? '<p class="reminder">Carry an umbrella</p>' : ''}
//            ${isHighTemp ? '<p class="reminder">Use sunscreen lotion</p>' : ''}
//          </div>
//        `;
//      });
//
//      weatherData.innerHTML = html;
//
//      // Prepare the data to send to the server
//      const postData = {
//        city: data.city.name,
//        weatherCondition: 'SomeWeatherCondition', // Replace with actual weather condition
//        // Add any other data you want to send to the server
//      };
//
//      // Send the data to the server using a POST request
//      fetch('/api/weather/save', {
//        method: 'POST',
//        headers: {
//          'Content-Type': 'application/json',
//        },
//        body: JSON.stringify(postData),
//      })
//        .then(response => response.json())
//        .then(serverResponse => {
//          console.log('Server Response:', serverResponse);
//        })
//        .catch(error => {
//          console.log('Error sending data to the server:', error);
//        });
//    })
//    .catch(error => {
//      console.log('Error:', error);
//      cityName.textContent = 'Error occurred while fetching weather data.';
//      weatherData.innerHTML = '';
//    });
//});



const cityForm = document.getElementById('cityForm');
const cityInput = document.getElementById('cityInput');
const cityName = document.getElementById('cityName');
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
      cityName.textContent = data.city.name;

      const uniqueDates = [];
      const uniqueWeatherData = [];
      let uniqueDatesCount = 0;

      data.list.forEach(item => {
        const date = new Date(item.dt_txt).toLocaleDateString('en-US', { weekday: 'long', month: 'short', day: 'numeric' });

        if (!uniqueDates.includes(date) && uniqueDatesCount < 3) {
          uniqueDates.push(date);
          uniqueWeatherData.push(item);
          uniqueDatesCount++;
        }
      });

      let html = '';
      uniqueWeatherData.forEach(item => {
        const date = new Date(item.dt_txt).toLocaleDateString('en-US', { weekday: 'long', month: 'short', day: 'numeric' });
        const highTemp = item.main.temp_max;
        const lowTemp = item.main.temp_min;
        const isRainPredicted = item.weather.some(w => w.main === 'Rain');
        const isHighTemp = highTemp > 40;

        html += `
          <div class="weather-card">
            <h3>${date}</h3>
            <p>High: ${highTemp}°C</p>
            <p>Low: ${lowTemp}°C</p>
            ${isRainPredicted ? '<p class="reminder">Carry an umbrella</p>' : ''}
            ${isHighTemp ? '<p class="reminder">Use sunscreen lotion</p>' : ''}
          </div>
        `;
      });

      weatherData.innerHTML = html;

      // Prepare the data to send to the server (only city name)
      const postData = {
        city: cityInput.value,
      };
      // ...

      console.log('Sending request to:', '/api/weather/save');
      console.log('Request method:', 'POST');
      console.log('Request data:', postData);

      // Send the data to the server using a POST request
      fetch('http://localhost:8080/api/weather/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(postData),
      })
        .then(response => response.json())
        .then(serverResponse => {
          console.log('Server Response:', serverResponse);
        })
        .catch(error => {
          console.log('Error sending data to the server:', error);
        });
    })
    .catch(error => {
      console.log('Error:', error);
      cityName.textContent = 'Error occurred while fetching weather data.';
      weatherData.innerHTML = '';
    });
});
