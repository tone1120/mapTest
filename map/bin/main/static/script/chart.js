
// root과 chart 만들기
var root = am5.Root.new("chartdiv");

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

var data = [{
    category: "JPTYO",
    value: 7.58
}, {
    category: "HKHKG",
    value: 38.22
},
{
    category: "SGSIN",
    value: 40.64
},
{
    category: "CNSHA",
    value: 31.01
},
{
    category: "KRINC",
    value: 5.29
},
{
    category: "JPOSA",
    value: 4.32
},
{
    category: "KRPUS",
    value: 16.65
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




