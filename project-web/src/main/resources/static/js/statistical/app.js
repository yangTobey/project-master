(function(){
	function seteDatetime(){
		var now = new Date();
		var formatter = '';
		formatter += now.getFullYear() + '年';
		// formatter += (now.getMonths() + 1) + '月';
		formatter += (now.getMonth() > 8 ? (now.getMonth() + 1) : '0' + (now.getMonth() + 1)) + '月';
		formatter += (now.getDate() > 9 ? now.getDate() : '0' + now.getDate()) + '日 ';
		formatter += (now.getHours() > 9 ? now.getHours() : '0' + now.getHours()) + ':';
		formatter += (now.getMinutes() > 9 ? now.getMinutes() : '0' + now.getMinutes()) + ':';
		formatter += now.getSeconds() > 9 ? now.getSeconds() : '0' + now.getSeconds();

		$('#currentDatetime').text(formatter);
	}

	seteDatetime();
	setInterval(seteDatetime, 1000)
})();

(function(){
    $.ajax({
        async: true,
        url:  'http://localhost:8080/login/login',
        type: 'GET',
        data: {
            userName: 'zhang',
            password: '321'
        },
        dataType: 'json',
        success: function (data) {
            $.ajax({
                async: true,
                url: 'http://localhost:8080/sysUser/sysUserInfo',
                type: 'GET',
                data: {
                    id: '1',
                    year: '321',
                    month:'10'
                },
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                },
                error: function (data) {
                    console.log(data);
                    alert("网络异常！");
                }
            });
        },
        error: function (v1, v2, v3) {
            console.log(v1);
            console.log(v2);
            console.log(v3);
        }
    });

	var charts = [];
	(function(){
		$('.pie-chart').each(function(){
			// var data = $(this).data();
			var pie = new utils.PercentPie({
				value: 0,
				domEle: this
			});

			charts.push(pie.chart);
		});
	})();

	// 工单统计
	var orderChart = (function(){
		var orderChart = echarts.init($('#orderTotalChart')[0]);
		charts.push(orderChart);
		return function(data){
			orderChart.setOption({
				tooltip: {
					trigger: 'axis',
					formatter: '{b0}<br>{a0}: {c0}<br>{a1}: {c1}%'
				},
				color: ['#a791c3', '#ffffff'],
				grid: {
					x: 50,
					y: 40,
					x2: 50,
					y2: 30
				},
				xAxis: [{
					type: 'category',
					boundaryGap: true,
					data: data.xAxis,
					// data: ['2017.5', '2017.6', '2017.7', '2017.8', '2017.9', '2017.10', '2017.11'],
					axisLabel: {
						textStyle: {
							color: '#ffffff'
						}
					}
				}],
				yAxis: [{
					type: 'value',
					// scale: true,
					name: '工单数量',
					nameTextStyle: {
						color: '#ffffff'
					},
					axisLabel: {
						textStyle: {
							color: '#ffffff'
						}
					},
					splitLine : {
						show: false
					},
					axisLine: {
						show: false
					}
				}, {
					type: 'value',
					// scale: true,
					name: '环比增长',
					nameTextStyle: {
						color: '#ffffff'
					},
					axisLabel: {
						textStyle: {
							color: '#ffffff'
						}
					},
					splitLine : {
						show: false
					},
					axisLine: {
						show: false
					}
				}],
				series: [{
					name:'工单数量',
					type:'bar',
					barWidth: 15,
					barGap: 0,
					data: data.data[0]
					// data: [248, 759, 589, 368, 431, 285, 541]
				}, {
					name:'工单环比',
					type:'line',
					lineStyle: {
						normal: {
							width: 3
						}
					},
					yAxisIndex: 1,
					data: data.data[1]
					// data: [248, 759, 589, 368, 431, 285, 541]
				}]
			});
		}
	})();

	// 能耗增长情况
	var energyChart = (function(){
		var energyChart = echarts.init($('#energyIncreaseChart')[0]);
		charts.push(energyChart);
		return function(data){
			energyChart.setOption({
				legend: {
					data: ['月耗电', '月耗水', '耗电环比', '耗水环比'],
					bottom: 2,
					itemWidth: 10,
					itemHeight: 4,
					itemGap: 4,
					textStyle: {
						color: '#ffffff',
						fontSize: 8
					}
				},
				tooltip: {
					trigger: 'axis',
					formatter: '{b0}<br>{a0}: {c0}<br>{a1}: {c1}<br>{a2}: {c2}%<br>{a3}: {c3}%'
				},
				color: ['#a791c3', '#177ac0', '#ffffff', '#ffffff', ],
				grid: {
					x: 50,
					y: 40,
					x2: 50,
					y2: 50
				},
				xAxis: [{
					type: 'category',
					boundaryGap: true,
					data: data.xAxis,
					// data: ['2017.5', '2017.6', '2017.7', '2017.8', '2017.9', '2017.10', '2017.11'],
					axisLabel: {
						textStyle: {
							color: '#ffffff',
							fontSize: 8
						}
					}
				}],
				yAxis: [{
					type: 'value',
					// scale: true,
					name: '消耗量',
					nameTextStyle: {
						color: '#ffffff',
						fontSize: 8
					},
					axisLabel: {
						textStyle: {
							color: '#ffffff',
							fontSize: 8
						}
					},
					axisLine: {
						show: false
					}
				}, {
					type: 'value',
					// scale: true,
					name: '环比增长',
					nameTextStyle: {
						color: '#ffffff',
						fontSize: 8
					},
					axisLabel: {
						textStyle: {
							color: '#ffffff',
							fontSize: 8
						}
					},
					axisLine: {
						show: false
					}
				}],
				series: [{
					name:'月耗电',
					type:'bar',
					barWidth: 8,
					barGap: 0,
					data: data.data[0]
					// data: [248, 759, 589, 368, 431, 285, 541]
				}, {
					name:'月耗水',
					type:'bar',
					barWidth: 8,
					data: data.data[1]
					// data: [143, 159, 170, 127, 141, 94, 113]
				}, {
					name:'耗电环比',
					type:'line',
					lineStyle: {
						normal: {
							width: 3
						}
					},
					yAxisIndex: 1,
					data: data.data[2]
					// data: [248, 759, 589, 368, 431, 285, 541]
				}, {
					name:'耗水环比',
					type:'line',
					lineStyle: {
						normal: {
							width: 3
						}
					},
					yAxisIndex: 1,
					data: data.data[3]
					// data: [143, 159, 170, 127, 141, 94, 113]
				}]
			});
		}
	})();

	// 合格率情况
	var passChart = (function(){
		var passChart = echarts.init($('#passRateChart')[0]);
		charts.push(passChart);
		return function(data){
			passChart.setOption({
				tooltip: {
					trigger: 'axis',
					formatter: '{b0}<br>{a0}: {c0}%<br>{a1}: {c1}%'
				},
				grid:{
					x:50,
					y:20,
					x2:30,
					y2:60
				},
				color: ['#a44f9a', '#5993c9'],
				legend: {
					data:[{
						name: '品质合格率',
						icon: 'roundRect'
					}, {
						name: '整改合格率',
						icon: 'roundRect'
					}],
					bottom: 5,
					textStyle: {
						color: '#ffffff'
					}
				},
				xAxis: {
					type: 'category',
					boundaryGap: false,
					data: data.xAxis,
					axisLabel: {
						color: '#ffffff'
					},
					axisLine: {
						lineStyle: {
							color: '#d9d9d9',
							width: 3
						}
					},
					axisTick: {
						show: false
					}
				},
				yAxis: {
					type: 'value',
					max: 100,
					axisLabel: {
						color: '#ffffff',
						formatter: '{value}%'
					},
					axisLine: {
						show: false
					},
					axisTick: {
						show: false
					},
					splitLine : {
						show: false
					}
				},
				series: [{
					name:'品质合格率',
					type:'line',
					lineStyle: {
						normal: {
							width: 3
						}
					},
					data: data.data[0]
					// data:[20, 40, 10, 30, 50, 70, 45]
				}, {
					name:'整改合格率',
					type:'line',
					lineStyle: {
						normal: {
							width: 3
						}
					},
					data: data.data[1]
					// data:[30, 20, 20, 10, 60, 80, 25]
				}]
			});
		}
	})();

	// 成本构成
	var costChart = (function(){
		var costChart = echarts.init($('#costComposeChart')[0])
		charts.push(costChart);
		return function(data){
			costChart.setOption({
				color: ['#20a0d1', '#a791c3', '#177ac0'],
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				series : [{
					name: '构成成本',
					type: 'pie',
					radius: '70%',
					center: ['50%', '50%'],
					data:data,
					// data:[
					// 	{value: 0, name: '构成成本一'},
					// 	{value: 0, name: '构成成本二'},
					// 	{value: 0, name: '构成成本三'}
					// ],
					itemStyle: {
						emphasis: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					}
				}]
			});
		}
	})();

	$(window).on('resize', function(){
		charts.forEach(function(chart){
			chart.resize();
		});
	});

	function update(){
		if(document.hidden)
			return;

		$('.pie-chart').each(function(){
			new utils.PercentPie({
				value: Math.round(Math.random() * 100),
				domEle: this
			});
		});

		// 工单统计
		orderChart({
			xAxis: ['2017.5', '2017.6', '2017.7', '2017.8', '2017.9', '2017.10', '2017.11'],
			data: utils.generateData(2, 7)
		});

		// 能耗增长情况
		energyChart({
			xAxis: ['2017.5', '2017.6', '2017.7', '2017.8', '2017.9', '2017.10', '2017.11'],
			data: utils.generateData(4, 7)
		});

		// 合格率情况
		passChart({
			xAxis: ['2017.5','2017.6','2017.7','2017.8','2017.9','2017.10','2017.11'],
			data: utils.generateData(2, 7, 100)
		});

		// 成本构成
		costChart([
			{value: Math.round(Math.random() * 1000), name: '构成成本一'},
			{value: Math.round(Math.random() * 1000), name: '构成成本二'},
			{value: Math.round(Math.random() * 1000), name: '构成成本三'}
		]);

		$('.value').each(function(){
			var data = $(this).data();
			$(this).animateNumber({
				number: Math.round((Math.random() * data.max))
			})
		});
	}
	update();
	setInterval(update, 2000);
})();

(function () {
    var geoCoordMap = {
        '上海': [121.4648, 31.2891],
        '东莞': [113.8953, 22.901],
        '东营': [118.7073, 37.5513],
        '中山': [113.4229, 22.478],
        '临汾': [111.4783, 36.1615],
        '临沂': [118.3118, 35.2936],
        '丹东': [124.541, 40.4242],
        '丽水': [119.5642, 28.1854],
        '乌鲁木齐': [87.9236, 43.5883],
        '佛山': [112.8955, 23.1097],
        '保定': [115.0488, 39.0948],
        '兰州': [103.5901, 36.3043],
        '包头': [110.3467, 41.4899],
        '北京': [116.4551, 40.2539],
        '北海': [109.314, 21.6211],
        '南京': [118.8062, 31.9208],
        '南宁': [108.479, 23.1152],
        '南昌': [116.0046, 28.6633],
        '南通': [121.1023, 32.1625],
        '厦门': [118.1689, 24.6478],
        '台州': [121.1353, 28.6688],
        '合肥': [117.29, 32.0581],
        '呼和浩特': [111.4124, 40.4901],
        '咸阳': [108.4131, 34.8706],
        '哈尔滨': [127.9688, 45.368],
        '唐山': [118.4766, 39.6826],
        '嘉兴': [120.9155, 30.6354],
        '大同': [113.7854, 39.8035],
        '大连': [122.2229, 39.4409],
        '天津': [117.4219, 39.4189],
        '太原': [112.3352, 37.9413],
        '威海': [121.9482, 37.1393],
        '宁波': [121.5967, 29.6466],
        '宝鸡': [107.1826, 34.3433],
        '宿迁': [118.5535, 33.7775],
        '常州': [119.4543, 31.5582],
        '广州': [113.5107, 23.2196],
        '廊坊': [116.521, 39.0509],
        '永清': [116.521, 39.0509],
        '延安': [109.1052, 36.4252],
        '张家口': [115.1477, 40.8527],
        '徐州': [117.5208, 34.3268],
        '德州': [116.6858, 37.2107],
        '惠州': [114.6204, 23.1647],
        '成都': [103.9526, 30.7617],
        '扬州': [119.4653, 32.8162],
        '承德': [117.5757, 41.4075],
        '拉萨': [91.1865, 30.1465],
        '无锡': [120.3442, 31.5527],
        '日照': [119.2786, 35.5023],
        '昆明': [102.9199, 25.4663],
        '杭州': [119.5313, 29.8773],
        '枣庄': [117.323, 34.8926],
        '柳州': [109.3799, 24.9774],
        '株洲': [113.5327, 27.0319],
        '武汉': [114.3896, 30.6628],
        '汕头': [117.1692, 23.3405],
        '江门': [112.6318, 22.1484],
        '沈阳': [123.1238, 42.1216],
        '沧州': [116.8286, 38.2104],
        '河源': [114.917, 23.9722],
        '泉州': [118.3228, 25.1147],
        '泰安': [117.0264, 36.0516],
        '泰州': [120.0586, 32.5525],
        '济南': [117.1582, 36.8701],
        '济宁': [116.8286, 35.3375],
        '海口': [110.3893, 19.8516],
        '万宁':[110.388793,18.796216],
        '淄博': [118.0371, 36.6064],
        '淮安': [118.927, 33.4039],
        '深圳': [114.5435, 22.5439],
        '清远': [112.9175, 24.3292],
        '温州': [120.498, 27.8119],
        '渭南': [109.7864, 35.0299],
        '湖州': [119.8608, 30.7782],
        '湘潭': [112.5439, 27.7075],
        '滨州': [117.8174, 37.4963],
        '潍坊': [119.0918, 36.524],
        '烟台': [120.7397, 37.5128],
        '玉溪': [101.9312, 23.8898],
        '珠海': [113.7305, 22.1155],
        '盐城': [120.2234, 33.5577],
        '盘锦': [121.9482, 41.0449],
        '石家庄': [114.4995, 38.1006],
        '福州': [119.4543, 25.9222],
        '秦皇岛': [119.2126, 40.0232],
        '绍兴': [120.564, 29.7565],
        '聊城': [115.9167, 36.4032],
        '肇庆': [112.1265, 23.5822],
        '舟山': [122.2559, 30.2234],
        '苏州': [120.6519, 31.3989],
        '莱芜': [117.6526, 36.2714],
        '菏泽': [115.6201, 35.2057],
        '营口': [122.4316, 40.4297],
        '葫芦岛': [120.1575, 40.578],
        '衡水': [115.8838, 37.7161],
        '衢州': [118.6853, 28.8666],
        '西宁': [101.4038, 36.8207],
        '西安': [109.1162, 34.2004],
        '贵阳': [106.6992, 26.7682],
        '连云港': [119.1248, 34.552],
        '邢台': [114.8071, 37.2821],
        '邯郸': [114.4775, 36.535],
        '郑州': [113.4668, 34.6234],
        '鄂尔多斯': [108.9734, 39.2487],
        '重庆': [107.7539, 30.1904],
        '金华': [120.0037, 29.1028],
        '铜川': [109.0393, 35.1947],
        '银川': [106.3586, 38.1775],
        '镇江': [119.4763, 31.9702],
        '长春': [125.8154, 44.2584],
        '长沙': [113.0823, 28.2568],
        '长治': [112.8625, 36.4746],
        '阳泉': [113.4778, 38.0951],
        '青岛': [120.4651, 36.3373],
        '韶关': [113.7964, 24.7028]
    };

    var GZData = [
        [{name: '深圳'}, {name: '北京', value: 5, area: ['北京富贵园', '北京国瑞城', '北京国瑞公寓', '哈德门广场', '京禧阁']}],
        [{name: '深圳'}, {name: '沈阳', value: 5, area: ['沈阳国瑞城', '沈阳汽配城']}],
        [{name: '深圳'}, {name: '郑州', value: 5, area: ['郑州国瑞城']}],
        [{name: '深圳'}, {name: '永清', value: 5, area: []}],
        [{name: '深圳'}, {name: '苏州', value: 5, area: []}],
        [{name: '深圳'}, {name: '重庆', value: 5, area: []}],
        [{name: '深圳'}, {name: '佛山', value: 5, area: []}],
        [{name: '深圳'}, {name: '海口', value: 5, area: ['海口国瑞城', '海口国瑞花园']}],
        [{name: '深圳'}, {name: '万宁', value: 5, area: []}],
        [{name: '深圳'}, {name: '深圳', value: 100, area: []}]
    ];

    var action = ['已受理派单', '工单处理完毕'];

    var mapCharts = echarts.init(document.getElementById('mapsChart'));
    mapCharts.setOption({
        tooltip: {
            trigger: 'item',
            backgroundColor:'rgba(3, 5, 17, 0.52)',
            showDelay : 0,
            hideDelay : 0,
            enterable: true,
            transitionDuration: 0,
            triggerOn: 'none',
            formatter : function(data, ticket, callback) {
                var res = '';

                GZData.forEach(function(item){
                    var area = item[1].area;
                    if(item[1].name === data.name && area.length){
                        res = area[ Math.floor(Math.random() * area.length) ] + action[ Math.floor(Math.random() * action.length) ];
                    }
                });

                return res;
            }
        },
        visualMap: {
            min: 0,
            max: 100,
            calculable: true,
            show:false,
            color: ['#ff3333', 'orange', 'yellow', 'lime', 'aqua']
        },
        geo: {
            map: 'china',
            label: {
                emphasis: {
                    show: false
                }
            },
			top: 5,
			bottom: 5,
            scaleLimit: {
            	max: 1,
            	min: 1
            },
            silent: true,
            roam: false,
            itemStyle: {
                normal: {
                    areaColor: '#0f7ba1',
                    borderColor: '#328ca0'
                },
                emphasis: {
                    areaColor: '#0f7ba1'
                }
            }
        },
        series: [{
            type: 'lines',
            coordinateSystem: 'geo',
            zlevel: 2,
            large: true,
            effect: {
                show: true,
                constantSpeed: 30,
                symbol: 'pin',
                symbolSize: 3,
                trailLength: 0,
            },
            lineStyle: {
                normal: {
                    color: '#a6c84c',
                    width: 1,
                    opacity: 0.4,
                    curveness: -0.2
                }
            },
            data: GZData.map(function(dataItem) {
                return [
                    {coord: geoCoordMap[ dataItem[0].name ]},
                    {coord: geoCoordMap[ dataItem[1].name ]}
                ];
            })
        }, {
            type: 'effectScatter',
            coordinateSystem: 'geo',
            zlevel: 2,
            rippleEffect: {
                brushType: 'stroke'
            },
            label: {
                normal: {
                    show: true,
                    position: 'left',
                    formatter: '{b}'
                }
            },
            data: GZData.map(function(dataItem) {
                return {
                    name: dataItem[1].name,
                    value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
                };
            })
        }]
    });

    function showEvent(){
        var map = [0, 1, 2, 7];
        var index = map[ Math.floor((Math.random() * map.length)) ];
        mapCharts.dispatchAction({
            type: 'showTip',
            seriesIndex: 1,
            dataIndex: index
        });
    }

    setInterval(showEvent, 2000)

    $(window).on('resize', function(){
		mapCharts.resize();
	});
})();