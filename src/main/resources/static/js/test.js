
const inputSelect = document.querySelector(".input-select")
const inputSubSelect = document.querySelector(".input-sub-select")

const subValues = Array.from(document.querySelectorAll('.sub-select-value'))

// Loc khoa hoc theo danh muc
if(inputSelect){
	inputSelect.onchange = function(){
		var htmls = subValues.map(function(value){
			if(inputSelect.value === value.getAttribute('type')){
				return `<option class="sub-select-value" 
		        			value="${value.value}" 
		        			type="${value.getAttribute('type')}">${value.text}</option>`
			}
		})
		
		inputSubSelect.innerHTML = htmls.join('')
	}	
}

// Chon tat ca, hoac chon don
const btnSelectAll = document.querySelector('.btn-select-all')
const btnSelects = document.querySelectorAll('.btn-select-one')

if(btnSelectAll && btnSelects){
	btnSelectAll.onchange = function(){
		btnSelects.forEach(function(btnSelect){
			if(btnSelectAll.checked == true){
				btnSelect.checked = true				
			}
			else btnSelect.checked = false
		})
	}
	
	btnSelects.forEach(function(btnSelect){
		btnSelect.onchange = function(){
			var numberIsChecked = document.querySelectorAll('input[name="idCourses[]"]:checked')
			if(numberIsChecked.length === btnSelects.length){
				btnSelectAll.checked = true
			}
			else btnSelectAll.checked = false
		}
	})
}
