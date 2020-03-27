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
import com.eosr14.motionlayoutexample.codelab.step.*
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
    ),
    Step(
        "Step 3",
        "경로 수정",
        "애니메이션 경로 수정",
        Step3Activity::class
    ),
    Step(
        "Step 4",
        "복잡한 경로 수정",
        "애니메이션 복잡한 경로 수정",
        Step4Activity::class
    ),
    Step(
        "Step 5",
        "모션 실행중 속성변경",
        "애니메이션 모션 실행중 속성 변경하기",
        Step5Activity::class
    ),
    Step(
        "Step 6",
        "사용자 정의 속성 변경",
        "애니메이션 사용자 정의 속성 변경하기",
        Step6Activity::class
    ),
    Step(
        "Step 7",
        "이벤트 및 복잡한 경로 제어",
        "애니메이션 이벤트 및 복잡한 경로 제어",
        Step7Activity::class
    )
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