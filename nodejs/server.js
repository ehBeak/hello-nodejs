// 서버를 띄우기 위한 기본 셋팅(express 라이브러리)
const express = require('express');
const app = express();
// bodyparser은 express내부에 포함되었음
const bodyParser = require('body-parser')
app.use(express.urlencoded({extended : true}));

// DB 연결
const MongoClient = require('mongodb').MongoClient;
MongoClient.connect('mongodb+srv://admin:1234@cluster0.hc9nj.mongodb.net/myFirstDatabase?retryWrites=true&w=majority',function (에러, client) {
    // 서버 띄울 포트 번호, 띄운 후 실행 코드
    app.listen(8080, function() {
    // 8080포트로 접속하면 콘솔에 아래 글 띄어주기
        console.log('listening on 8080');
    });
});


// get('경로',function)
app.get('/pet', function(요청, 응답) {
    응답.send('펫용품을 쇼핑할 수 있는 페이지입니다.');
});

// 
app.get('/beauty', function(요청, 응답) {
    응답.send('뷰티용품을 쇼핑할 수 있는 페이지입니다.');
});

app.get('/', function(요청,응답) {
    // 파일을 보냄
    응답.sendFile(__dirname + '/index.html')
})

app.get('/write', function(요청,응답) {
    응답.sendFile(__dirname + '/write.html')
});

// 응답 받으면 요청에 내용 들어 있음
app.post('/add', function(요청, 응답) {
    응답.send('전송완료')
    console.log(요청.body.title);
    console.log(요청.body.date);
});