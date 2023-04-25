import { Component } from '@angular/core';
declare var require: any;
var CanvasJS = require('../../../assets/canvasjs.min');
 
CanvasJS.addColorSet("customColorSet",["#ffcb06", "#ce1249", "#3a943c", "#7f3e83", "#812900", "#2078b6", "#df7f2e", "#e3e3e3"]);

@Component({
  selector: 'doughnut-chart-dark-theme',
  templateUrl: 'chart.component.html',
})
export class DoughnutChartDarkThemeComponent {
	chartOptions = {
		animationEnabled: true,
		theme: "dark2",
		colorSet: "customColorSet",
		title:{
		  text: "Global Waste Treatment and Disposal"
		},
		data: [{
		  type: "doughnut",
		  indexLabel: "{name}: {y}",
		  innerRadius: "90%",
		  yValueFormatString: "#,##0.00'%'",
		  dataPoints: [
			{ y: 33, name: "Open Dump" },
			{ y: 25, name: "Landfill" },
			{ y: 13.5, name: "Recycling" },
			{ y: 11, name: "Incineration" },
			{ y: 7.7, name: "Sanitary Landfill (with landfill gas collection)" },
			{ y: 5.5, name: "Composting" },
			{ y: 4, name: "Controlled Landfill" },
			{ y: 0.3, name: "Others" }
		  ]
		}]
	}	
}
