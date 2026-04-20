const API_KEY = '1679380e9af5c46e8767f5c6b9ac2103'

let chart = null

const getWeather = async() => {
    let city = document.getElementById('city-name').value

    const history = await fetch(`https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${API_KEY}&units=metric`)

    const data = await history.json()

    const labels = data.list.slice(0,8).map(item=> item.dt_txt)
    const temps = data.list.slice(0,8).map(item=> item.main.temp)

    drawChart(labels,temps)

}

const drawChart= (labels,temps) => {
    if(chart) chart.destroy()

        try{
            chart = new Chart(document.getElementById('weather-chart'),{
                type: "line",
                data: {
                    labels:labels,
                    datasets:[{
                        label: "Temperature(C)",
                        data:temps,
                        borderWidth: 2
                }]}
            })
        } catch(e) {
            console.log(e)
        }
}