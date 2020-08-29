<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>C4U 너만의 기사</title>
    <link rel="stylesheet" href="../resources/css/semi_mypage.css">
    <link rel="stylesheet" href="../resources/css/semi_mypage_download.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div>
	<%@ include file="mypage/header.jsp"%>

	<%@ include file="mypage/leftmenu.jsp"%>
        <h1 id="score-title">내가 받은 자료</h1>

        <div>
            <select id="category-Name" name="category-Name">
                <option value>카테고리 선택</option>
                <option value="기능사">기능사</option>
                <option value="기사">기사</option>
                <option value="산업기사">산업기사</option>
                <option value="기타">기타</option>
            </select>
        </div>
        <table id="score-table" >
            <thead>
            <tr>
                <th>No.</th>
                <th>Title.</th>
                <th>용량</th>
                <th>받은날짜</th>
                <th>다시받기</th>
            </tr>
            </thead>
            <tr>
                <td>1</td>
                <td>2019 12회 정보처리기사 필기</td>
                <td>1.2MB</td>
                <td>2020.08.06</td>
                <td><a href="#">2019.12.PDF</a></td>

            </tr>
            <tr>
                <td>2</td>
                <td>2019 6회 정보처리기사 필기</td>
                <td>10TB</td>
                <td>2020.08.05</td>
                <td><a href="#">2019.06.PDF</a></td>
            </tr>
            <tr>
                <td>3</td>
                <td>2020 2회 기능사 필기</td>
                <td>500GB</td>
                <td>2020.08.04</td>
                <td><a href="#">2020.02.PDF</a></td>
            </tr>
            <tr>
                <td>4</td>
                <td>2020 2회 기능사 필기</td>
                <td>500GB</td>
                <td>2020.08.04</td>
                <td><a href="#">2020.02.PDF</a></td>
            </tr>
            <tr>
                <td>5</td>
                <td>2020 2회 기능사 필기</td>
                <td>500GB</td>
                <td>2020.08.04</td>
                <td><a href="#">2020.02.PDF</a></td>
            </tr>
            <tr>
                <td>6</td>
                <td>2020 2회 기능사 필기</td>
                <td>500GB</td>
                <td>2020.08.04</td>
                <td><a href="#">2020.02.PDF</a></td>
            </tr>
            <tr>
                <td>7</td>
                <td>2020 2회 기능사 필기</td>
                <td>500GB</td>
                <td>2020.08.04</td>
                <td><a href="#">2020.02.PDF</a></td>
            </tr>
            <div class="paging">
                <a href="#"><<</a>
                <a href="#"><</a>
                <a href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
                <a href="#">></a>
                <a href="#">>></a>
              </div>
        </table>
    </div>
    <%@ include file="mypage/footer.jsp"%>
</body>
</html>