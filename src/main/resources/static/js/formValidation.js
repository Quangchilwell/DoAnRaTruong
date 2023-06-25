
var reservationForm = document.querySelector('.reservation-form-body')
var formLogin = document.querySelector('#form-login')
var formRegister = document.querySelector('#form-register')

function ValidateForm(form)
{
    function validation(inputElement, textError){
        var textMessage = ''
        if(inputElement.value == ''){
            textMessage = 'Vui lòng nhập trường này'
            textError.innerText = textMessage;
            inputElement.classList.add('active-invalid')
        }
        else{
            var regex = ''
            switch(inputElement.name){
                case "phone":
                    regex = /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/   
                    if(!regex.test(inputElement.value)){
                        textMessage = 'Số điện thoại không hợp lệ'
                        textError.innerText = textMessage;
                        inputElement.classList.add('active-invalid')
                    }
                    break

                case "email":
                    regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
                    if(!regex.test(inputElement.value)){
                        textMessage = 'Email không hợp lệ'
                        textError.innerText = textMessage;
                        inputElement.classList.add('active-invalid')
                    }
                    break

                case "password":
                    var number = 3
                    if(inputElement.value.length < number){
                        textMessage = `Mật khẩu phải có tối thiểu ${number} kí tự`
                        textError.innerText = textMessage;
                        inputElement.classList.add('active-invalid')
                    }
                    break
                
                case "password-confirm":
                    var password = form.querySelector("input[name='password']")
                    if(inputElement.value !== password.value){
                        textMessage = `Mật khẩu nhập lại không chính xác`
                        textError.innerText = textMessage;
                        inputElement.classList.add('active-invalid')
                    }
                    break   
                
                default:
                    console.log('Nhận dạng không có trong hệ thống')
            }
           
        }
        return textMessage
    }

    var inputElements = form.querySelectorAll('.form-input')
    var btnSubmitForm = form.querySelector('.btn-submit-form')

    Array.from(inputElements).forEach(function(inputElement){
        var textError = inputElement.parentElement.querySelector('.text-error')
        inputElement.onblur = function(){
           validation(inputElement, textError)
        }

        inputElement.oninput = function(){
            textError.innerText = ''
            inputElement.classList.remove('active-invalid')
        }

    })

    btnSubmitForm.onclick = function(e){
        var checkValid = ''
        Array.from(inputElements).forEach(function(inputElement){
            var textError = inputElement.parentElement.querySelector('.text-error')
            checkValid = validation(inputElement, textError)
            if(checkValid != ''){
                e.preventDefault()
            } 
        })
    }
}

if(reservationForm){
    ValidateForm(reservationForm)
}

if(formLogin){
    ValidateForm(formLogin)
}

if(formRegister){
    ValidateForm(formRegister)
}
