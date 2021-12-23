// import { Component, OnInit } from '@angular/core';
// import { Camera,CameraOptions } from '@ionic-native/camera/ngx';
// import { File } from '@ionic-native/file/ngx';
// import { ActionSheetController } from '@ionic/angular';
// @Component({
//   selector: 'app-insert-signalement',
//   templateUrl: './insert-signalement.page.html',
//   styleUrls: ['./insert-signalement.page.scss'],
// })
// export class InsertSignalementPage implements OnInit {

//   croppedImagepath = "";
//   isLoading = false;

//   imagePickerOptions = {
//     maximumImagesCount: 1,
//     quality: 50
//   };

//   options: CameraOptions = {
//     quality: 30,
//     destinationType: this.camera.DestinationType.DATA_URL,
//     encodingType: this.camera.EncodingType.JPEG,
//     mediaType: this.camera.MediaType.PICTURE
//   }

//   constructor(private camera: Camera,public actionSheetController: ActionSheetController,private file:File) { }
//   pickImage(sourceType) {
//     const options: CameraOptions = {
//       quality: 100,
//       sourceType: sourceType,
//       destinationType: this.camera.DestinationType.DATA_URL,
//       encodingType: this.camera.EncodingType.JPEG,
//       mediaType: this.camera.MediaType.PICTURE
//     }
//     this.camera.getPicture(options).then((imageData) => {
//       // imageData is either a base64 encoded string or a file URI
//       this.croppedImagepath = 'data:image/jpeg;base64,' + imageData;
//     }, (err) => {
//       // Handle error
//     });
//   }

//   async selectImage() {
//     const actionSheet = await this.actionSheetController.create({
//       header: "Select Image source",
//       buttons: [{
//         text: 'Load from Library',
//         handler: () => {
//           this.pickImage(this.camera.PictureSourceType.PHOTOLIBRARY);
//         }
//       },
//       {
//         text: 'Use Camera',
//         handler: () => {
//           this.pickImage(this.camera.PictureSourceType.CAMERA);
//         }
//       },
//       {
//         text: 'Cancel',
//         role: 'cancel'
//       }
//       ]
//     });
//     await actionSheet.present();
//   }

//   ngOnInit() {
//   }

// }


import { Component, OnInit } from '@angular/core';
import { Camera, CameraOptions } from '@ionic-native/camera/ngx';
import { File } from '@ionic-native/file/ngx';
import { ActionSheetController } from '@ionic/angular';
@Component({
  selector: 'app-insert-signalement',
  templateUrl: './insert-signalement.page.html',
  styleUrls: ['./insert-signalement.page.scss'],
})
export class InsertSignalementPage implements OnInit {
  imgURL;

  constructor(private camera: Camera, public actionSheetController: ActionSheetController, private file: File) { }
  test(){
    alert("gege");
  }
  insertSignalement(){
    alert("youuuuhou lasa le signalement letye");
  }
  getCamera(){
    this.camera.getPicture({
      sourceType: this.camera.PictureSourceType.CAMERA,
      destinationType: this.camera.DestinationType.FILE_URI
    }).then((res)=>{
      this.imgURL = res;
    }).catch((err)=>{
      console.log(err);
      alert(err);
    })
  }
  getGallery(){
    this.camera.getPicture({
      sourceType: this.camera.PictureSourceType.PHOTOLIBRARY,
      destinationType: this.camera.DestinationType.DATA_URL
    }).then((res)=>{
      this.imgURL ='data:image/jpeg;base64,'+ res;
    }).catch((err)=>{
      console.log(err);
      alert(err);
    })
  }

  ngOnInit() {
  }

}