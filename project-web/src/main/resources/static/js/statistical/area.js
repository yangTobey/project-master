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

	// 收费情况
	var chargesChart = (function(){
		var chargesChart = echarts.init($('#chargesChart')[0]);
		charts.push(chargesChart);
		return function(data){
			chargesChart.setOption({
				tooltip: {
					trigger: 'axis',
					formatter: '{b0}<br>{a0}: {c0}%<br>{a1}: {c1}%'
				},
				grid:{
					x:50,
					y:60,
					x2:30,
					y2:30
				},
				color: ['#a44f9a', '#5993c9'],
				legend: {
					data:[{
						name: '当期收入',
						icon: 'roundRect'
					}, {
						name: '清欠收款',
						icon: 'roundRect'
					}],
					top: 15,
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
					// max: 100,
					axisLabel: {
						color: '#ffffff',
						// formatter: '{value}%'
					},
					axisLine: {
						show: false
					},
					axisTick: {
						show: false
					}
				},
				series: [{
					name:'当期收入',
					type:'line',
					lineStyle: {
						normal: {
							width: 4
						}
					},
					data: data.data[0]
					// data:[20, 40, 10, 30, 50, 70, 45]
				}, {
					name:'清欠收款',
					type:'line',
					lineStyle: {
						normal: {
							width: 4
						}
					},
					data: data.data[1]
					// data:[30, 20, 20, 10, 60, 80, 25]
				}]
			});
		}
	})();

	// 地产收款情况
	var estateReceivableChart = (function(){
		var estateReceivableChart = echarts.init($('#estateReceivableChart')[0]);
		charts.push(estateReceivableChart);
		return function(data){
			estateReceivableChart.setOption({
				legend: {
					// data: ['收款情况一', '收款情况二'],
					// bottom: 2,
					// itemWidth: 10,
					// itemHeight: 4,
					// itemGap: 4,
					// textStyle: {
					// 	color: '#ffffff',
					// 	// fontSize: 12
					// }
					show: false
				},
				tooltip: {
					trigger: 'axis',
					formatter: '{b0}<br>{a0}: {c0}<br>{a1}: {c1}'
				},
				color: ['#1c7ac0', '#a790c4'],
				grid: {
					x: 50,
					y: 40,
					x2: 50,
					y2: 70
				},
				xAxis: [{
					type: 'category',
					boundaryGap: true,
					data: data.xAxis,
					// data: ['2017.5', '2017.6', '2017.7', '2017.8', '2017.9', '2017.10', '2017.11'],
					axisLabel: {
						textStyle: {
							color: '#ffffff',
							fontSize: 10
						},
						rotate: 45
					},
					axisLine: {
						lineStyle: {
							color: '#d9d9d9',
							width: 3
						}
					}
				}],
				yAxis: [{
					type: 'value',
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
					name:'收款情况一',
					type:'bar',
					barGap: 0,
					stack: '收款',
					data: data.data[0]
					// data: [248, 759, 589, 368, 431, 285, 541]
				}, {
					name:'收款情况二',
					type:'bar',
					stack: '收款',
					data: data.data[1]
					// data: [143, 159, 170, 127, 141, 94, 113]
				}]
			});
		}
	})();

	// 小业主收款情况
	var ownerReceivableChart = (function(){
		var ownerReceivableChart = echarts.init($('#ownerReceivableChart')[0]);
		charts.push(ownerReceivableChart);
		return function(data){
			ownerReceivableChart.setOption({
				legend: {
					data: ['应收款小业主', '已收款小业主', '其他'],
					bottom: 2,
					itemWidth: 10,
					itemHeight: 4,
					itemGap: 4,
					textStyle: {
						color: '#ffffff',
						// fontSize: 10
					}
				},
				tooltip: {
					trigger: 'axis',
					formatter: '{b0}<br>{a0}: {c0}<br>{a1}: {c1}<br>{a2}: {c2}%'
				},
				color: ['#7b639d', '#7bcce7', '#ffffff'],
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
					},
					axisLine: {
						lineStyle: {
							color: '#d9d9d9',
							width: 3
						}
					}
				}],
				yAxis: [{
					type: 'value',
					// scale: true,
					name: '收款小业主',
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
					name: '其他',
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
					name:'应收款小业主',
					type:'bar',
					// barWidth: 20,
					barGap: 0,
					data: data.data[0]
					// data: [248, 759, 589, 368, 431, 285, 541]
				},{
					name:'已收款小业主',
					type:'bar',
					// barWidth: 20,
					data: data.data[1]
					// data: [248, 759, 589, 368, 431, 285, 541]
				}, {
					name:'其他',
					type:'line',
					lineStyle: {
						normal: {
							width: 4
						}
					},
					yAxisIndex: 1,
					data: data.data[2]
					// data: [143, 159, 170, 127, 141, 94, 113]
				}]
			});
		}
	})();

	// 收入情况
	var incomeChart = (function(){
		var incomeChart = echarts.init($('#incomeChart')[0]);
		charts.push(incomeChart);
		return function(data){
			incomeChart.setOption({
				legend: {
					data: ['实际收入', '预算收入'],
					bottom: 2,
					itemWidth: 10,
					itemHeight: 4,
					itemGap: 4,
					textStyle: {
						color: '#ffffff',
						// fontSize: 12
					}
				},
				tooltip: {
					trigger: 'axis',
					formatter: '{b0}<br>{a0}: {c0}<br>{a1}: {c1}'
				},
				color: ['#7b639d', '#7bcce7'],
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
					},
					axisLine: {
						lineStyle: {
							color: '#d9d9d9',
							width: 3
						}
					}
				}],
				yAxis: [{
					type: 'value',
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
					name:'实际收入',
					type:'bar',
					barGap: 0,
					data: data.data[0]
					// data: [248, 759, 589, 368, 431, 285, 541]
				}, {
					name:'预算收入',
					type:'bar',
					data: data.data[1]
					// data: [143, 159, 170, 127, 141, 94, 113]
				}]
			});
		}
	})();

	// 利润情况
	var profitChart = (function(){
		var profitChart = echarts.init($('#profitChart')[0]);
		charts.push(profitChart);
		return function(data){
			profitChart.setOption({
				legend: {
					data: ['实际利润', '环比增长'],
					bottom: 2,
					itemWidth: 10,
					itemHeight: 4,
					itemGap: 4,
					textStyle: {
						color: '#ffffff',
						// fontSize: 10
					}
				},
				tooltip: {
					trigger: 'axis',
					formatter: '{b0}<br>{a0}: {c0}<br>{a1}: {c1}%'
				},
				color: ['#7b639d', '#ffffff'],
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
					},
					axisLine: {
						lineStyle: {
							color: '#d9d9d9',
							width: 3
						}
					}
				}],
				yAxis: [{
					type: 'value',
					// scale: true,
					name: '实际利润',
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
					name:'实际利润',
					type:'bar',
					barWidth: 20,
					barGap: 0,
					data: data.data[0]
					// data: [248, 759, 589, 368, 431, 285, 541]
				}, {
					name:'环比增长',
					type:'line',
					lineStyle: {
						normal: {
							width: 4
						}
					},
					yAxisIndex: 1,
					data: data.data[1]
					// data: [143, 159, 170, 127, 141, 94, 113]
				}]
			});
		}
	})();

	// 财务支出情况
	var financialExpensesChart = (function(){
		var financialExpensesChart = echarts.init($('#financialExpensesChart')[0]);
		charts.push(financialExpensesChart);
		return function(data){
			financialExpensesChart.setOption({
				legend: {
					data: ['实际支出', '预算支出'],
					bottom: 2,
					itemWidth: 10,
					itemHeight: 4,
					itemGap: 4,
					textStyle: {
						color: '#ffffff',
						// fontSize: 10
					}
				},
				tooltip: {
					trigger: 'axis',
					formatter: '{b0}<br>{a0}: {c0}<br>{a1}: {c1}'
				},
				color: ['#7b639d', '#7bcce7'],
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
					},
					axisLine: {
						lineStyle: {
							color: '#d9d9d9',
							width: 3
						}
					}
				}],
				yAxis: [{
					type: 'value',
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
					name:'实际支出',
					type:'bar',
					barGap: 0,
					data: data.data[0]
					// data: [248, 759, 589, 368, 431, 285, 541]
				}, {
					name:'预算支出',
					type:'bar',
					data: data.data[1]
					// data: [143, 159, 170, 127, 141, 94, 113]
				}]
			});
		}
	})();

	// 支出占比
	var expenditureChart = (function(){
		var expenditureChart = echarts.init($('#expenditureChart')[0])
		charts.push(expenditureChart);
		return function(data){
			expenditureChart.setOption({
				// color: ['#20a0d1', '#a791c3', '#177ac0'],
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				series : [{
					name: '支出占比',
					type: 'pie',
					radius: '90%',
					center: ['50%', '50%'],
					data:data,
					label: {
						normal: {
							position: 'inside',
							fontSize: 8,
							formatter: '{b}\n{d}%'
						}
					},
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

		// 收入情况
		estateReceivableChart({
			xAxis: ['礼券减免', '空置', '3%补贴款', '销售配合', '开办费', '物业费贴款', '其他地产应付款'],
			data: utils.generateData(2, 7)
		});

		// 小业主收款情况
		ownerReceivableChart({
			xAxis: ['2017.5', '2017.6', '2017.7', '2017.8', '2017.9', '2017.10', '2017.11'],
			data: utils.generateData(3, 7)
		});

		// 收入情况
		incomeChart({
			xAxis: ['2017.5', '2017.6', '2017.7', '2017.8', '2017.9', '2017.10', '2017.11'],
			data: utils.generateData(2, 7)
		});

		// 利润情况
		profitChart({
			xAxis: ['2017.5', '2017.6', '2017.7', '2017.8', '2017.9', '2017.10', '2017.11'],
			data: utils.generateData(2, 7)
		});

		// 财务支出情况
		financialExpensesChart({
			xAxis: ['2017.5', '2017.6', '2017.7', '2017.8', '2017.9', '2017.10', '2017.11'],
			data: utils.generateData(2, 7)
		});

		// 收费情况
		chargesChart({
			xAxis: ['2017.5','2017.6','2017.7','2017.8','2017.9','2017.10','2017.11'],
			data: utils.generateData(2, 7)
		});

		// 支出占比
		expenditureChart([
			{value: Math.round(Math.random() * 1000), name: '人员费用'},
			{value: Math.round(Math.random() * 1000), name: '行政费用'},
			{value: Math.round(Math.random() * 1000), name: '物料消耗费'},
			{value: Math.round(Math.random() * 1000), name: '能源消耗费用'},
			{value: Math.round(Math.random() * 1000), name: '工程设备维修费'},
			{value: Math.round(Math.random() * 1000), name: '清洁卫生费'},
			{value: Math.round(Math.random() * 1000), name: '绿化养护费'},
			{value: Math.round(Math.random() * 1000), name: '秩序维护费'},
			{value: Math.round(Math.random() * 1000), name: '社区活动费'},
			{value: Math.round(Math.random() * 1000), name: '其他费用'}
		]);

		$('.value').each(function(){
			var data = $(this).data();
			$(this).animateNumber({
				number: Math.round((Math.random() * data.max)),
				numberStep: function(now, tween) {
					var floored_number = Math.floor(now),

					target = $(tween.elem);

					target.text( ('' + floored_number).replace(/(\d+?)(?=(?:\d{3})+$)/g, '$1,') );
				}
			})
		});
	}
	update();
	setInterval(update, 2000);
})();