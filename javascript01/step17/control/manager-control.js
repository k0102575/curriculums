/* 학생 정보를 다루는 서비스를 정의한다.
*/

const express = require('express')
const datasource = require('../util/datasource')
const managerDao = require('../dao/manager-dao')
const memberDao = require('../dao/member-dao')
const managerService = require('../service/manager-service')

const connection = datasource.getConnection()
managerDao.setConnection(connection)
memberDao.setConnection(connection)
managerService.setMemberDao(memberDao)
managerService.setManagerDao(managerDao)

const router = express.Router()

router.get('/list.do', (request, response) => {
  var pageNo = 1,
  pageSize = 3
  if(request.query.pageNo){
    pageNo = parseInt(request.query.pageNo)
  }

  if(request.query.pageSize){
    pageSize = parseInt(request.query.pageSize)
  }

  managerService.list(pageNo, pageSize, function(results, totalCount) {
    // response.setHeader('Content-Thpe , text/html;charset=UTF-8')
    var lastPageNo = parseInt(totalCount / pageSize) + (((totalCount % pageSize) > 0) ? 1 : 0)
    response.render('manager/index', {
      data: results,
      pageNo: pageNo,
      nextPageNo : pageNo +1,
      prevPageNo : pageNo -1,
      disabledPrevBtn : (pageNo == 1) ? 'disabled' : '',
      disabledNextBtn : (pageNo == lastPageNo) ? 'disabled' : ''
    })
  }, function(error) {
    response.render('error', {'message': '강사 목록 데이터를 가져오는중 오류가 발생했습니다!'})
    console.log(error)
  })
}) // router.get(list.do)

router.get('/detail.do', function(request, response) {
  var no = parseInt(request.query.no)
  managerService.detail(no,
    function(result) {
      response.render('manager/view',{
        detail: true,
        data: result,
        'checkedWorking' :(result.work == 'Y' ? 'checked' : '')
      })
    },
    function(error) {
      response.render('error', {'message': '학생 데이터를 가져오는중 오류가 발생했습니다!'})
      console.log(error)
    })
})  // router.get(detail.do)

router.post('/update.do', function(request, response) {

  managerService.update({
    no: request.body.no,
    name: request.body.name,
    tel: request.body.tel,
    email: request.body.email,
    password: '1111',
    posi: request.body.posi,
    fax: request.body.fax,
    path: request.body.path
  },
    function(result) {
      response.redirect('list.do')  // 정상적으로 변경을 완료하면 이 목록으로 가라
    },
    function(error) {
      response.render('error', {'message': '학생 데이터를 변경하는중 오류가 발생했습니다!'})
      console.log(error)
    })
})  // router.post(update.do)

router.get('/delete.do', function(request, response) {
  var no = parseInt(request.query.no)
  managerService.delete(no,
    function(result) {
      response.redirect('list.do')
    },
    function(error) {
      response.render('error', {'message': '학생 데이터를 삭제하는중 오류가 발생했습니다!'})
      console.log(error)
    })
}) // router.get(delete)

router.get('/form.do', function(request, response) {
  response.render('manager/view')
}) // router.get(form)

router.post('/add.do', function(request, response){
  managerService.insert({
    name: request.body.name,
    tel: request.body.tel,
    email: request.body.email,
    password: '1111',
    posi: request.body.posi,
    fax: request.body.fax,
    path: request.body.path
  },
    function(result) {
      response.redirect('list.do')  // 정상적으로 변경을 완료하면 이 목록으로 가라
    },
    function(error) {
      response.render('error', {'message': '학생 데이터를 등록하는중 오류가 발생했습니다!'})
      console.log(error)
    })
}) // router.post(add)

// // 라우터를 만들어서 라우터에 등록해라
// // 라우터에 다가 이 서비스에 대해 이 함수를 호출해라
// // 등록후 라우터를 리턴한다.
// // app.js에서 라우터가

module.exports = router
