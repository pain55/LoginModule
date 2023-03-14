import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { userInfo } from 'os';
import { LoginInfo } from 'src/app/model/login-info';
import { User } from 'src/app/model/user';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  // password hide button trigger variable
  hide: boolean = true;
  // variable used to set duration of snack bar
  durationInSec =2;

  // to initialize an object of type loginInfo interface
  loginInfo = {} as LoginInfo;


  constructor(private _snack:MatSnackBar,private loginService: LoginService, private router:Router) { }
  ngOnInit(): void {
  }


  loginForm() {

    // validation on username
    if( this.loginInfo.username == undefined ||this.loginInfo.username.trim() =="" || this.loginInfo.username ==null  ) {
      this._snack.open("Invalid Username","ok",{
        duration: 1000 * this.durationInSec
      });
      return;
    }
    // validation on password
    else if( this.loginInfo.password == undefined || this.loginInfo.password.trim() =="" || this.loginInfo.password ==null ) {
      this._snack.open("Invalid Password","ok",{
        duration: 1000 * this.durationInSec
      });
      return;
    }
    else {
      this.loginService.generateToken(this.loginInfo).subscribe({
        next:(data:any) => {

          console.log(data.token);

          this.loginService.loginUser(data.token);

          this.loginService.getCurrentUser().subscribe({
           next:(currentUser:any) =>{
             this.loginService.setUser(currentUser);
             console.log(currentUser);

            //  redirecting to admin if logged in user is admin
            if(this.loginService.getUserRole()=="ROLE_ADMIN") {
              this.router.navigate(["admin"]);
            }
            else {
              this.router.navigate(["user"]);
            }
           },
          })
        },
        error(err) {
          console.log(err.error);
        },
      })
    }
  }
}
