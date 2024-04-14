$(function () {
	$('#searchBtn').on('click', function () {
		let searchPort = $('#searchPort').val();
		
		// 현재 시간을 구하고 ISO 형식으로 변환
		let now = new Date();
		// 년, 월, 일, 시간, 분, 초 추출
		let year = now.getFullYear();
		let month = String(now.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
		let day = String(now.getDate()).padStart(2, '0');
		let hours = String(now.getHours()).padStart(2, '0');
		let minutes = String(now.getMinutes()).padStart(2, '0');
		let seconds = String(now.getSeconds()).padStart(2, '0');

		// "YYYY-MM-DD HH:MM:SS" 형식의 문자열 생성
		let clickTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;


		let sendData = { "searchPort": searchPort, "clickTime": clickTime }

		console.info("확인 항구: " + searchPort);
		console.info("확인 시간: " + clickTime);

		// 대기선박, 작업선박, 평균 수용량 가져오기
		$.ajax({
			url: "/study/fun/working",
			method: "GET",
			data: sendData,
			success: function (resp) {

				let waitingVessels = resp.waitingVessels; // 확인 시점에서 대기중인 선박들
				let workingVessels = resp.workingVessels; // 확인 시점에서 작업중인 선박들

				let portAvgCnt = resp.portAvgCnt[0].vesselCnt;// 항구 평균 수용량 List<ChartEntity>으로 받음


				let waitingShipList = {}; // 선박타입: 선박 대수 순으로 딕셔너리 저장
				let waitingTypeList = []; // 선박 타입이 들어감
				let waitingHtml = ""; // 테이블로 들어감

				let workingShipList = {};
				let workingTypeList = [];
				let workingHtml = "";


				/* 
					대기 선박
				*/
				for (let i = 0; i < waitingVessels.length; i++) {

					let waitingShipType = waitingVessels[i].shipType;

					if (!waitingShipList[waitingShipType]) {
						waitingShipList[waitingShipType] = 0;
						waitingTypeList.push(waitingShipType);
					}

					// shipList의 shipType 수량 증가
					waitingShipList[waitingShipType]++;

				}


				for (let j = 0; j < waitingTypeList.length; j++) { // 선박 종류 별로 테이블 만들기
					let waitingShipType = waitingTypeList[j];
					let waitingShipCnt = waitingShipList[waitingShipType];

					waitingHtml += `<tr><td>${waitingShipType}</td><td>${waitingShipCnt}</td></tr>`
				}
				waitingHtml += `<tr><td>Total</td><td>${waitingVessels.length}</td></tr>` // 전체 선박 대수 추가



				/* 
					작업 선박
				*/
				for (let i = 0; i < workingVessels.length; i++) {

					let workingShipType = workingVessels[i].shipType;

					if (!workingShipList[workingShipType]) {
						workingShipList[workingShipType] = 0;
						workingTypeList.push(workingShipType);
					}

					// shipList의 shipType 수량 증가
					workingShipList[workingShipType]++;

				}


				for (let j = 0; j < workingTypeList.length; j++) { // 선박 종류 별로 테이블 만들기
					let workingShipType = workingTypeList[j];
					let workingShipCnt = workingShipList[workingShipType];

					workingHtml += `<tr><td>${workingShipType}</td><td>${workingShipCnt}</td></tr>`
				}
				workingHtml += `<tr><td>Total</td><td>${workingVessels.length}</td></tr>` // 전체 선박 대수 추가




				/* 
					평균과 비교
				*/
				let compareAvg = workingVessels.length - portAvgCnt;

				let message = "";
				if (compareAvg > 0) { message += `평균보다 ${compareAvg.toFixed(0)}대 많은 선박이 작업하고 있습니다.`; }
				else if (compareAvg == 0) { message += `평균보다 ${compareAvg.toFixed(0)}대 많은 선박이 작업하고 있습니다.`; }
				else { message += `평균보다 ${Math.abs(compareAvg).toFixed(0)}대 적은 선박이 작업하고 있습니다.`; }

				// 웹으로 구현
				$('#waiting').append(waitingHtml);
				$('#working').append(workingHtml);
				$('#vesselMessage').append(message);
			}

		});


	});
})
