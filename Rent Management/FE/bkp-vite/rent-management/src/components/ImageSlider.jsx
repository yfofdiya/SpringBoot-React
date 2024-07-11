import React, { useState } from 'react'
import { BsArrowLeftShort, BsArrowRightShort } from "react-icons/bs"
import { RxDotFilled } from "react-icons/rx"

const ImageSlider = () => {

  const images = [
    '/house_images/1.png',
    '/house_images/2.png',
    '/house_images/3.jpeg',
    '/house_images/4.jpeg'
  ]

  const [currentIndex, setCurrentIndex] = useState(0)

  const prevImage = () => {
    const isFirstImage = currentIndex === 0
    const newIndex = isFirstImage ? images.length - 1 : currentIndex - 1
    setCurrentIndex(newIndex)
  }

  const nextImage = () => {
    const isLastImage = currentIndex === images.length - 1
    const newIndex = isLastImage ? 0 : currentIndex + 1
    setCurrentIndex(newIndex)
  }

  const goToImage = (index) => {
    setCurrentIndex(index)
  }

  return (
    <div className='max-w-[350px] h-[200px] w-full m-auto py-2 px-4 relative group'>
      <div style={{ backgroundImage: `url(${images[currentIndex]})` }} className='w-full h-full rounded-2xl bg-center bg-cover duration-500'>
      </div>
      <div className='hidden group-hover:block absolute top-[50%] -translate-x-0 translate-y-[-50%] left-5 text-2xl rounded-full p-2 bg-black/20 text-center cursor-pointer'>
        <BsArrowLeftShort onClick={prevImage} />
      </div>
      <div className='hidden group-hover:block absolute top-[50%] -translate-x-0 translate-y-[-50%] right-5 text-2xl rounded-full p-2 bg-black/20 text-center cursor-pointer'>
        <BsArrowRightShort onClick={nextImage} />
      </div>
      <div className='flex top-4 justify-center py-2'>
        {
          images.map((image, index) => (
            <div
              key={index}
              onClick={() => goToImage(index)}
              className='text-2xl cursor-pointer'>
              <RxDotFilled />
            </div>
          ))
        }
      </div>
    </div>
  )
}

export default ImageSlider