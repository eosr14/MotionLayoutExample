package com.eosr14.motionlayoutexample.codelab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.eosr14.motionlayoutexample.R
import com.eosr14.motionlayoutexample.codelab.step.Step1Activity
import com.eosr14.motionlayoutexample.codelab.step.Step2Activity
import kotlinx.android.synthetic.main.activity_code_lab.*

private val data = listOf(
    Step(
        "Step 1",
        "모션 레이아웃 애니메이션",
        "모션 레이아웃으로 기본 애니메이션 작성하기.",
        Step1Activity::class
    ),
    Step(
        "Step 2",
        "Drag 기반 애니메이션",
        "Drag 이벤트를 통해 애니메이션 제어하기",
        Step2Activity::class
    )
//    Step("Step 2",기
//        "Animating based on drag events",기
//        "Learn how to control animations with drag events. This will not display any animation until you complete the step in the codelab.",
//        Step2Activity::class
//    ),
//    Step("Step 3",
//        "Modifying a path",
//        "Learn how to use KeyFrames to modify a path between start and end.",
//        Step3Activity::class
//    ),
//    Step("Step 4",
//        "Building complex paths",
//        "Learn how to use KeyFrames to build complex paths through multiple KeyFrames.",
//        Step4Activity::class
//    ),
//    Step("Step 5",
//        "Changing attributes with motion",
//        "Learn how to resize and rotate views during animations.",
//        Step5Activity::class
//    ),
//    Step("Step 6",
//        "Changing custom attributes",
//        "Learn how to change custom attributes during motion.",
//        Step6Activity::class
//    ),
//    Step("Step 7",
//        "OnSwipe with complex paths",
//        "Learn how to control motion through complex paths with OnSwipe.",
//        Step7Activity::class
//    ),
//    Step("Completed: Steps 2-7",
//        "Steps 2-7 completed",
//        "All changes in steps 2-7 applied",
//        Step7CompletedActivity::class,
//        highlight = true
//    ),
//    Step("Step 8",
//        "Running motion with code",
//        "Learn how to use MotionLayout to build complex collapsing toolbar animations.",
//        Step8Activity::class
//    ),
//    Step("Completed: Step 8 ",
//        "Implements running motion with code",
//        "Changes applied from step 8",
//        Step8CompletedActivity::class,
//        highlight = true
//    )
)


class CodeLabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_lab)

        rv_code_lab.run { adapter = MainAdapter(data) }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, CodeLabActivity::class.java))
        }
    }
}

class MainAdapter(private val data: List<Step>) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MainViewHolder(view as CardView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])
    }

}

class MainViewHolder(private val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
    private val header: TextView = cardView.findViewById(R.id.header)
    private val description: TextView = cardView.findViewById(R.id.description)
    private val caption: TextView = cardView.findViewById(R.id.caption)

    fun bind(step: Step) {
        header.text = step.number
        description.text = step.name
        caption.text = step.caption

        val context = cardView.context

        cardView.setOnClickListener {
            val intent = Intent(context, step.activity.java)
            context.startActivity(intent)
        }

        val color = if (step.highlight) {
            context.resources.getColor(R.color.secondaryLightColor)
        } else {
            context.resources.getColor(R.color.primaryTextColor)
        }

        header.setTextColor(color)
        description.setTextColor(color)
    }

}