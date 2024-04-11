
let searchPort;
let vesselCnt;
var root;

$(function () {
	$("#portSearch").on("click", function () {
		let portCd = $("#portCd").val();
		modal.style.display = 'block';

		$.ajax({
			url: "/study/chart/chartSearch", // html에서는 url에서 contextPath가 필요
			method: "GET",
			data: { "portCd": portCd }
			, success: function (resp) {
				searchPort = resp.portCd;
				vesselCnt = resp.vesselCnt;
	
				console.log(root);
				creatChart(searchPort, vesselCnt);
				
				
				function creatChart(searchPort, searchPort){
					if(root != null) root.dispose();
					
				// root과 chart 만들기
				root = am5.Root.new("chartdiv");
				root.setThemes([
					am5themes_Animated.new(root)
				]);

				var chart = root.container.children.push(
					am5xy.XYChart.new(root, {
						panY: false,
						layout: root.verticalLayout
					})
				);

				// 데이터 정의

				let port = searchPort;
				let congestion = vesselCnt;

				var data = [{
					category: port,
					value: congestion
				}];
				// y축 생성
				let yAxis = chart.yAxes.push(
					am5xy.ValueAxis.new(root, {
						renderer: am5xy.AxisRendererY.new(root, {})
					})
				);

				// x축 생성
				let xAxis = chart.xAxes.push(
					am5xy.CategoryAxis.new(root, {
						maxDeviation: 0.2,
						renderer: am5xy.AxisRendererX.new(root, {}),
						categoryField: "category"
					})
				);
				xAxis.data.setAll(data);

				// 시리즈 생성
				var series = chart.series.push(
					am5xy.ColumnSeries.new(root, {
						name: "평균 선박 수용량",
						xAxis: xAxis,
						yAxis: yAxis,
						valueYField: "value",
						categoryXField: "category",
						tootip: am5.Tooltip.new(root, {})
					})
				);
				series.data.setAll(data);

				// 막대 바 넓이 조정
				series.columns.template.setAll({
					width: 35
				});

				// legend 추가
				var legend = chart.children.push(am5.Legend.new(root, {}));
				legend.data.setAll(chart.series.values);
				}

				
				
				console.log(root.dom.id);

		
				
				
			}
		})
	})
})

const modal = document.querySelector('.modal');
const modalOpen = document.querySelector('.modal_btn');
const modalClose = document.querySelector('.close_btn');

//닫기 버튼을 눌렀을 때 모달팝업이 닫힘
modalClose.addEventListener('click',function(){
   //display 속성을 none으로 변경
    modal.style.display = 'none';
});


