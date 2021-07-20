# ActivityResultAPIFinishChildActivity
This project shows an issue related to the new Activity Result API that doesn't allow to finish child activity from a parent Activity because we don't have the RequestCode

https://github.com/4brunu/ActivityResultAPIFinishChildActivity/blob/404885f3623c30869097278a0f2ee87432a22557/app/src/main/java/net/medicineone/myapplication/MainActivity.kt#L28-L46
```
        val buttonStartActivityForResult = findViewById<Button>(R.id.buttonStartActivityForResult)
        buttonStartActivityForResult.setOnClickListener {
            val intent = Intent(this, ChildActivity::class.java)
            startActivityForResult(intent, CHILD_ACTIVITY_REQUEST_CODE)
            // Finish the child activity after 5 seconds
            Handler(Looper.getMainLooper()).postDelayed({
                finishActivity(CHILD_ACTIVITY_REQUEST_CODE)
            }, 5000)
        }

        val buttonActivityResultAPI = findViewById<Button>(R.id.buttonActivityResultAPI)
        buttonActivityResultAPI.setOnClickListener {
            val intent = Intent(this, ChildActivity::class.java)
            activityResultLauncher.launch(intent)
            Handler(Looper.getMainLooper()).postDelayed({
                // Since I don't have a request code, this doesn't work!
                finishActivity(CHILD_ACTIVITY_REQUEST_CODE)
            }, 5000)
        }

```
