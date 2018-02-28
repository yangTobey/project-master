var utils = {};

(function(){

	function PercentPie(option){
		this.color = option.color || ['rgba(167,145,195,1)','rgba(167,145,195,0.1)'];
		this.fontSize = option.fontSize || 14;
		this.domEle = option.domEle;
		this.value = option.value;
		this.init();
	}

	PercentPie.prototype.init = function(){
		var option = {
			color: this.color,
			series: [{
				type: 'pie',
				radius: ['92%', '100%'],
				avoidLabelOverlap: false,
				hoverAnimation: false,
				label: {
					normal: {
						show: false,
						position: 'center',
						textStyle: {
							fontSize: this.fontSize,
							fontWeight: 'bold'
						},
						formatter:'{b}{c}%'
					}
				},
				data: [{
					value: this.value,
					label:{
						normal:{
							show: true
						}
					}
				}, {
					value: 100 - this.value
				}]
			}]
		};

		this.chart = echarts.init(this.domEle)
		this.chart.setOption(option);
	};

	function generateData(groupLength, length, max){
		var data = [];
		var i = 0, j = 0;
		while(i < (groupLength || 2)){
			data.push([]);
			while(j < (length || 7)){
				data[ data.length - 1 ].push(Math.round(Math.random()* (max || 1000)));
				j++;
			}
			i++;
			j = 0;
		}
		return data;
	}

	utils.PercentPie = PercentPie;
	utils.generateData = generateData;
})();