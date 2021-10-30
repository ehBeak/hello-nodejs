require('dotenv').config();

// 서버를 띄우기 위한 기본 셋팅(express 라이브러리)
const express = require('express');
const app = express();
// bodyparser은 express내부에 포함되었음
const bodyParser = require('body-parser')
app.use(express.urlencoded({extended : true}));
app.set('view engine', 'ejs');

// DB 연결
const MongoClient = require('mongodb').MongoClient;
// post, delete요청을 위한 method override
const methodOverride = require('method-override')
app.use(methodOverride('_method'))

app.use('/public', express.static('public'));

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
    //응답.sendFile(__dirname + '/index.html')
    응답.render('index.ejs');
})

app.get('/write', function(요청,응답) {
    //응답.sendFile(__dirname + '/write.html')
    응답.render('write.ejs');
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

app.delete('/delete', function(요청, 응답) {
    요청.body._id = parseInt(요청.body._id);
    console.log(요청.body);
    db.collection('post').deleteOne(요청.body, function(에러, 결과){
        console.log('삭제완료');
        응답.status(200).send({ message : '성공했습니다'});
        //응답.status(400).send({ message : '성공했습니다'});
    })
})


app.get('/detail/:id', function(요청, 응답) {
    db.collection('post').findOne({_id : parseInt(요청.params.id)}, function(에러, 결과) {
        console.log(결과);
        응답.render('detail.ejs', { data : 결과});
    })
    
})

app.get('/edit/:id', function(요청, 응답) {
    db.collection('post').findOne({_id : parseInt(요청.params.id)}, function(에러, 결과){
        응답.render('edit.ejs', {post : 결과});
    })
})

app.put('/edit', function(요청, 응답) {
    db.collection('post').updateOne({ _id : parseInt(요청.body.id) },{ $set : { 제목: 요청.body.title, 날짜: 요청.body.date }},function(에러, 결과) {
        console.log('수정완료')
        응답.redirect('/list')
    })
})

const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const session = require('express-session');

app.use(session({secret : '비밀코드', resave : true, saveUninitialized: false}));
app.use(passport.initialize());
app.use(passport.session()); 


app.get('/login', function(요청, 응답){
    응답.render('login.ejs')
});

app.post('/login', passport.authenticate('local', {failureRedirect : '/fail'}), function(요청, 응답){
    응답.redirect('/')
});

app.get('/mypage', 로그인했니, function(요청, 응답){
    console.log(요청.user);
    응답.render('mypage.ejs', {사용자 : 요청.user})
});

function 로그인했니(요청, 응답, next) {
    if(요청.user){
        next()
    } else {
        응답.send('로그인안하셨는데요?')
    }
}

passport.use(new LocalStrategy({
    usernameField: 'id',
    passwordField: 'pw',
    session: true,
    passReqToCallback: false,
  }, function (입력한아이디, 입력한비번, done) {
    //console.log(입력한아이디, 입력한비번);
    db.collection('login').findOne({ id: 입력한아이디 }, function (에러, 결과) {
      if (에러) return done(에러)
  
      if (!결과) return done(null, false, { message: '존재하지않는 아이디요' })
      if (입력한비번 == 결과.pw) {
        return done(null, 결과)
      } else {
        return done(null, false, { message: '비번틀렸어요' })
      }
    })
  }));

  passport.serializeUser(function (user, done) {
    done(null, user.id)
  });
  


  passport.deserializeUser(function (아이디, done) {
    db.collection('login').findOne({id : 아이디}, function(에러, 결과){
        done(null, 결과)
    })
  }); 