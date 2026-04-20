const http = require('http')
const os = require('os')
const path = require('path')

const EventEmitter = require('events')

const eventEmitter = new EventEmitter()

//Paths to check - /os , /path , /event

eventEmitter.on('request-received',(url)=> {
    console.log(`Request Recived for URL : ${url}`);
})

eventEmitter.on('event-page-visited',(date)=> {
    console.log(`Event Page opened at ${date}`);
})

const server = http.createServer((req,res) => {

    eventEmitter.emit('request-received',req.url)
    res.writeHead(200, {'Content-Type' : 'text/html'})

    if(req.url==='/') {
        
        res.write('<h1>Welcome to NodeJS Server</h1>')  

    } else if(req.url==='/os') {

        res.write('<h1>OS Module Information</h1>')
        res.write(`<p>OS Platform : ${os.platform()}</p>`)
        res.write(`<p>OS Architecture : ${os.arch()}</p>`)
        res.write(`<p>OS Free Memory : ${os.freemem()}</p>`)
        res.write(`<p>OS Total Memory : ${os.totalmem()}</p>`)

    } else if(req.url === '/path') {
        const filepath = __filename

        res.write('<h1>Path Information</h1>')
        res.write(`<p>File Name: ${path.basename(filepath)}</p>`)
        res.write(`<p>Directory Name : ${path.dirname(filepath)}</p>`)
        res.write(`<p>Extension Name : ${path.extname(filepath)}</p>`)
        
    } else if(req.url === '/event') {

        eventEmitter.emit('event-page-visited', new Date().toLocaleString())

        res.write('<h1>Check Console for event message!</h1>')
    } else 
        res.write('<h1>404 - Page Not Found!</h1>')

    res.end()
})

const PORT = 3000

server.listen(PORT,() => {
    console.log(`Server running at http://localhost:${PORT}`)
})