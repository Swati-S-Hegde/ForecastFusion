//const cityForm = document.getElementById('cityForm');
//const cityInput = document.getElementById('cityInput');
//const weatherData = document.getElementById('weatherData');
//
//const maxRetries = 3;
//const retryInterval = 2000; // 2 seconds
//
//cityForm.addEventListener('submit', (event) => {
//  event.preventDefault();
//
//  const city = cityInput.value;
//  // Replace with your OpenWeatherMap API key
//  const apiKey = '811c58cffcdcbb85dad52cc5b324ded0';
//
//  // Function to fetch data and handle retries and caching
//  const fetchDataWithRetryAndCache = (retriesLeft) => {
//    fetch(`https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${apiKey}&units=metric`)
//      .then((response) => {
//        if (!response.ok) {
//          throw new Error('Network response was not ok');
//        }
//        return response.json();
//      })
//      .then((data) => {
//        // Process and display weather data
//        const cityName = data.city.name;
//
//        const uniqueDates = [];
//        const uniqueWeatherData = [];
//
//        // Get today's date
//        const today = new Date();
//        today.setHours(0, 0, 0, 0); // Set the time to midnight
//
//        data.list.forEach((item) => {
//          // Convert the date from UTC to local time
//          const utcDate = new Date(item.dt * 1000);
//          const localDate = new Date(utcDate.getTime() + utcDate.getTimezoneOffset() * 60000);
//
//          // Check if the date is greater than or equal to today and not already in the uniqueDates array
//          if (localDate >= today && !uniqueDates.includes(localDate.toDateString()) && uniqueDates.length < 3) {
//            uniqueDates.push(localDate.toDateString());
//
//            const highTemp = item.main.temp_max;
//            const lowTemp = item.main.temp_min;
//            const isRainPredicted = item.weather.some((w) => w.main === 'Rain');
//            const isHighTemp = highTemp > 40;
//
//            // Construct an object with all fields
//            const weatherEntry = {
//              date: localDate.toLocaleDateString('en-US', { weekday: 'long', month: 'short', day: 'numeric' }),
//              highTemperature: highTemp,
//              lowTemperature: lowTemp,
//              carryUmbrella: isRainPredicted,
//              useSunscreen: isHighTemp,
//            };
//
//            // Add the entry to the list
//            uniqueWeatherData.push(weatherEntry);
//          }
//        });
//
//        let html = '';
//        uniqueWeatherData.forEach((item) => {
//          html += `
//          <div class="weather-card">
//            <h3>${item.date}</h3>
//            <p>High: ${item.highTemperature}°C</p>
//            <p>Low: ${item.lowTemperature}°C</p>
//            ${item.carryUmbrella ? '<p class="reminder">Carry an umbrella</p>' : ''}
//            ${item.useSunscreen ? '<p class="reminder">Use sunscreen lotion</p>' : ''}
//          </div>
//        `;
//        });
//
//        // Display the weather data in the UI
//        weatherData.innerHTML = html;
//
//        // Prepare the data to send to the server
//        const postData = {
//          city: cityName,
//          weatherCondition: '', // Set the weather condition here
//          highTemperature: '', // Set the high temperature here
//          lowTemperature: '', // Set the low temperature here
//          carryUmbrella: false, // Set the carry umbrella value here
//          useSunscreen: false, // Set the use sunscreen value here
//          weatherEntries: uniqueWeatherData,
//        };
//
//        // Send the data to the server using a POST request
//        fetch('http://localhost:8080/api/weather/save', {
//          method: 'POST',
//          headers: {
//            'Content-Type': 'application/json',
//          },
//          body: JSON.stringify(postData),
//        })
//          .then((response) => {
//            if (!response.ok) {
//              throw new Error('Network response was not ok');
//            }
//            return response.json();
//          })
//          .then((serverResponse) => {
//            console.log('Server Response:', serverResponse);
//          })
//          .catch((error) => {
//            console.error('Error sending data to the server:', error);
//          });
//      })
//      .catch((error) => {
//        console.error('Error:', error);
//
//        if (retriesLeft > 0) {
//          // Retry fetching data after a delay
//          setTimeout(() => {
//            fetchDataWithRetryAndCache(retriesLeft - 1);
//          }, retryInterval);
//        } else {
//          // Fallback to displaying cached data
//          const cachedData = getCachedData(city);
//          if (cachedData) {
//            displayData(cachedData);
//          } else {
//            console.error('No cached data available');
//          }
//        }
//      });
//  };
//
//  // Start fetching data with retries and caching
//  fetchDataWithRetryAndCache(maxRetries);
//});
//
//function displayData(data) {
//  // Display data in the UI
//}
//
//function getCachedData(city) {
//  // Retrieve cached data using an appropriate cache key
//}
//
//
//
const cityForm = document.getElementById('cityForm');
const cityInput = document.getElementById('cityInput');
const weatherData = document.getElementById('weatherData');

const maxRetries = 3;
const retryInterval = 2000; // 2 seconds

cityForm.addEventListener('submit', (event) => {
  event.preventDefault();

  const city = cityInput.value;
  // Replace with your OpenWeatherMap API key
  const apiKey = '811c58cffcdcbb85dad52cc5b324ded0';

  // Function to fetch data and handle retries and caching
  const fetchDataWithRetryAndCache = (retriesLeft) => {
    fetch(`https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${apiKey}&units=metric`)
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((data) => {
        // Process and display weather data
        const cityName = data.city.name;

        const uniqueDates = [];
        const uniqueWeatherData = [];

        // Get today's date
        const today = new Date();
        today.setHours(0, 0, 0, 0); // Set the time to midnight

        data.list.forEach((item) => {
          // Convert the date from UTC to local time
          const utcDate = new Date(item.dt * 1000);
          const localDate = new Date(utcDate.getTime() + utcDate.getTimezoneOffset() * 60000);

          // Check if the date is greater than or equal to today and not already in the uniqueDates array
          if (localDate >= today && !uniqueDates.includes(localDate.toDateString()) && uniqueDates.length < 3) {
            uniqueDates.push(localDate.toDateString());

            const highTemp = item.main.temp_max;
            const lowTemp = item.main.temp_min;
            const isRainPredicted = item.weather.some((w) => w.main === 'Rain');
            const isHighTemp = highTemp > 40;

            // Format date in IST (Indian Standard Time) without time
            const formattedDate = localDate.toLocaleString('en-IN', {
              timeZone: 'Asia/Kolkata',
              year: 'numeric',
              month: '2-digit',
              day: '2-digit',
            });

            // Construct an object with all fields
            const weatherEntry = {
              date: formattedDate,
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
        uniqueWeatherData.forEach((item) => {
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
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.json();
          })
          .then((serverResponse) => {
            console.log('Server Response:', serverResponse);
          })
          .catch((error) => {
            console.error('Error sending data to the server:', error);
          });
      })
      .catch((error) => {
        console.error('Error:', error);

        if (retriesLeft > 0) {
          // Retry fetching data after a delay
          setTimeout(() => {
            fetchDataWithRetryAndCache(retriesLeft - 1);
          }, retryInterval);
        } else {
          // Fallback to displaying cached data
          const cachedData = getCachedData(city);
          if (cachedData) {
            displayData(cachedData);
          } else {
            console.error('No cached data available');
          }
        }
      });
  };

  // Start fetching data with retries and caching
  fetchDataWithRetryAndCache(maxRetries);
});

function displayData(data) {
  // Check if the data is empty or not
  if (data.weatherEntries.length === 0) {
    weatherData.innerHTML = '<p>No weather data available for this city.</p>';
    return;
  }

  let html = '';
  data.weatherEntries.forEach((item) => {
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
}

function getCachedData(city) {
  // Generate a cache key based on the city name
  const cacheKey = `weatherData_${city}`;

  // Retrieve cached data from local storage
  const cachedData = localStorage.getItem(cacheKey);

  if (cachedData) {
    // Parse the cached JSON data
    try {
      return JSON.parse(cachedData);
    } catch (error) {
      console.error('Error parsing cached data:', error);
    }
  }

  // If no cached data is available, return null
  return null;
}


