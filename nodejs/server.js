// 서버를 띄우기 위한 기본 셋팅(express 라이브러리)
const express = require('express');
const app = express();
// bodyparser은 express내부에 포함되었음
const bodyParser = require('body-parser')
app.use(express.urlencoded({extended : true}));
app.set('view engine', 'ejs');

// DB 연결
const MongoClient = require('mongodb').MongoClient;

var db;

MongoClient.connect('mongodb+srv://admin:1234@cluster0.hc9nj.mongodb.net/myFirstDatabase?retryWrites=true&w=majority',function (에러, client) {
    // 연결되면 할 일
    if(에러) return console.log(에러);

    db = client.db('todoapp');// todoapp이라는 db에 연결

    /*db.collection('post').insertOne({이름 : 'John', 나이 : 20}, function(에러, 결과) { // _id
        console.log('저장완료');
    });*/ // post테이블..?에 데이터삽입

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
    db.collection('counter').findOne({name : '게시물갯수'}, function(에러, 결과) {
        var 총게시물갯수 = 결과.totalPost

        db.collection('post').insertOne({_id : 총게시물갯수+1, 제목 : 요청.body.title, 날짜 : 요청.body.date}, function(에러, 결과) { // _id
            console.log('저장완료');

            // {어떤 데이터를 수정할지}, {$operator : {수정 값}}, function(){}
            db.collection('counter').updateOne({name : '게시물갯수'},{ $inc : {totalPost : 1}}, function(에러, 결과){
                // update가 끝나면 해야할 일
                if(에러) {return console.log(에러)}
            });
        }); 
    });
    
});

 

app.get('/list', function(요청, 응답) {

    db.collection('post').find().toArray(function(에러, 결과) {
        console.log(결과);
        응답.render('list.ejs', {posts : 결과});
    }); // 모든 데이터 가져오기
});
